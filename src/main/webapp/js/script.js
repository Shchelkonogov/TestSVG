jQuery(window).on('load', function() {
    var panZoom = svgPanZoom('#imap', {
        zoomEnabled: true,
        controlIconsEnabled: true,
        fit: true,
        center: true,
        minZoom: 0.5,
        maxZoom: 15,
        zoomScaleSensitivity: 0.5
    });

    jQuery(window).resize(function(){
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

    jQuery.ajax({
        url: "load",
        method: "post",
        data: userObj,
        error: function(message) {
            console.log(message);
        },
        success: function(data) {
            for(var i in data) {
                if (data.hasOwnProperty(i)) {
                    jQuery("#" + data[i].name, svgDom).changeVisible();
                    jQuery("#" + data[i].name, svgDom).text(data[i].data);

                    jQuery("#" + data[i].name + "_tit", svgDom).text(data[i].title);

                    jQuery("#" + data[i].name + "_col", svgDom).changeColor(data[i].color);

                    jQuery("#" + data[i].name + "_pic", svgDom).changeVisible();
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

function showDialog(){
    jsCall();
}