$(window).load(function() {
    panZoomInstance = svgPanZoom('#imap', {
        zoomEnabled: true,
        controlIconsEnabled: true,
        fit: true,
        center: true,
        minZoom: 0.5,
        maxZoom: 15,
        zoomScaleSensitivity: 0.5
    });

    var svgobject = document.getElementById('imap');
    if ('contentDocument' in svgobject) {
        var svgdom = svgobject.contentDocument;
    }

    var urlParams = new URLSearchParams(location.search);

    var userObj = {
        "objId" : urlParams.get("objectId")
    };

    $.ajax({
        url: "load",
        method: "post",
        data: userObj,
        error: function(message) {
            console.log(message);
        },
        success: function(data) {
            for(var i in data) {
                $("#" + data[i].name, svgdom).changeVisible();
                $("#" + data[i].name, svgdom).text(data[i].data);

                $("#" + data[i].name + "_col", svgdom).changeColor(data[i].color);

                $("#" + data[i].name + "_pic", svgdom).changeVisible();
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