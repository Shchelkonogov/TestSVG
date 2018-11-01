$(window).load(function() {
    var panZoom = svgPanZoom('#imap', {
        zoomEnabled: true,
        controlIconsEnabled: true,
        fit: true,
        center: true,
        minZoom: 0.5,
        maxZoom: 15,
        zoomScaleSensitivity: 0.5
    });

    $(window).resize(function(){
        panZoom.resize();
        panZoom.fit();
        panZoom.center();
    });

    var svgObject = document.getElementById('imap');
    if ('contentDocument' in svgObject) {
        var svgDom = svgObject.contentDocument;
    }

    var search = location.search;
    var userObj = {
        "objId" : search.substring(search.indexOf("objectId") + 9)
    };

    // Не работает в старых версиях chrome(44)
    // var urlParams = new URLSearchParams(location.search);
    // var userObj = {
    //     "objId" : urlParams.get("objectId")
    // };

    $.ajax({
        url: "load",
        method: "post",
        data: userObj,
        error: function(message) {
            console.log(message);
        },
        success: function(data) {
            for(var i in data) {
                if (data.hasOwnProperty(i)) {
                    $("#" + data[i].name, svgDom).changeVisible();
                    $("#" + data[i].name, svgDom).text(data[i].data);

                    $("#" + data[i].name + "_col", svgDom).changeColor(data[i].color);

                    $("#" + data[i].name + "_pic", svgDom).changeVisible();
                }
            }
        }
    });
});

jQuery.fn.changeColor = function (color) {
    return this.each(function() {
        jQuery(this).attr("fill", color);
    });
};

jQuery.fn.changeVisible = function () {
    return this.each(function() {
        jQuery(this).attr("opacity", 1);
    });
};