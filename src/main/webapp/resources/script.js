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

    setInterval(func, 2000);
});

function func() {
    var svgobject = document.getElementById('imap');
    if ('contentDocument' in svgobject)
        var svgdom = svgobject.contentDocument;

    var userObj = {
        "userName" : "vlad"
    };

    var url = "test";

    $.ajax({
        url: url,
        method: "post",
        data: userObj,
        error: function(message) {
            console.log(message);
        },
        success: function(data) {
            $("#qwerty", svgdom).text(data);
            console.log(data);
        }
    });
}