jQuery(window).on('load', function() {
    var panZoom = svgPanZoom('#svgDocument', {
        zoomEnabled: true,
        controlIconsEnabled: true,
        fit: true,
        center: true,
        minZoom: 0.5,
        maxZoom: 15,
        zoomScaleSensitivity: 0.5
    });

    var svgObject = document.getElementById('svgDocument');
    if ('contentDocument' in svgObject) {
        var svgDom = svgObject.contentDocument;
    }

    jQuery(window).resize(function(){
        var width = jQuery('#svgDocument').width() - 40;

        var oldAttr = jQuery('#update-button', svgDom).attr('transform');
        var newAttr = oldAttr.replace(/translate[(]\d+[ ]/, 'translate(' + width + ' ');
        jQuery('#update-button', svgDom).attr('transform', newAttr);

        oldAttr = jQuery('#redirect-button', svgDom).attr('transform');
        newAttr = oldAttr.replace(/translate[(]\d+[ ]/, 'translate(' + (width - 5) + ' ');
        jQuery('#redirect-button', svgDom).attr('transform', newAttr);

        panZoom.resize();
        panZoom.fit();
        panZoom.center();
    });

    if (svgDom.getElementById('mnemonicSVG') != null) {
        var width = jQuery("#svgDocument").width() - 40;

        var refreshButton = document.createElementNS('http://www.w3.org/2000/svg', 'g');
        refreshButton.setAttribute('id', 'update-button');
        refreshButton.setAttribute('transform', 'translate(' + width + ' ' + 7 + ') scale(0.5)');
        refreshButton.setAttribute('class', 'svg-pan-zoom-control');
        refreshButton.addEventListener('click', function(){
            var timer = setInterval(function() {
                var oldAttr = jQuery('#rotateItem', svgDom).attr('transform');
                var rotateValue = oldAttr.match(/rotate[(]\d+[,]/)[0].match(/\d+/)[0];
                var newRotate = parseInt(rotateValue) + 10;
                if (newRotate === 360) {
                    newRotate = 0;
                }
                var newAttr = oldAttr.replace(/rotate[(]\d+[,]/, 'rotate(' + newRotate + ',');
                jQuery('#rotateItem', svgDom).attr('transform', newAttr);
            }, 30);
            loadData("instLoad", timer);
        }, false);

        var refreshButtonTitle = document.createElementNS('http://www.w3.org/2000/svg', 'title');
        refreshButtonTitle.appendChild(document.createTextNode('Загрузить мгновенные данные'));
        refreshButton.appendChild(refreshButtonTitle);

        var refreshButtonBackground = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
        refreshButtonBackground.setAttribute('width', '63.25');
        refreshButtonBackground.setAttribute('height', '63.16');
        refreshButtonBackground.setAttribute('class', 'svg-pan-zoom-control-background');
        refreshButton.appendChild(refreshButtonBackground);

        var refreshButtonShape = document.createElementNS('http://www.w3.org/2000/svg', 'path');
        refreshButtonShape.setAttribute('d', 'm15.052646,24.168085c3.738297,7.170433 4.468278,9.348475 3.233338,9.642054c-2.416078,0.577682 -1.932068,3.882431 1.448059,9.874184c4.242844,7.516037 12.545685,12.130386 20.344238,11.306778c3.027908,-0.319366 6.449707,-0.981079 7.605286,-1.467072c1.15535,-0.487976 0.446228,0.419708 -1.577087,2.015549c-10.117355,7.983566 -22.613266,7.426559 -32.581238,-1.450043c-3.975221,-3.537842 -9.446106,-13.881218 -9.446106,-17.857368c0,-1.126137 -0.900574,-2.21727 -2.002365,-2.425995c-1.802261,-0.340256 -1.560257,-1.232887 2.407028,-8.890688c2.424011,-4.68338 4.710967,-8.849577 5.074158,-9.260718c0.366791,-0.411087 2.836609,3.421432 5.49469,8.51332l0,0l-0.000001,0zm32.142945,13.823168c-3.738281,-7.170465 -4.468262,-9.348505 -3.233337,-9.642084c2.416077,-0.578285 1.932068,-3.882538 -1.452026,-9.874138c-4.239212,-7.516021 -12.541685,-12.130409 -20.340269,-11.306762c-3.028152,0.319366 -6.449693,0.980393 -7.605289,1.467041c-1.155593,0.487975 -0.446166,-0.419678 1.57286,-2.015397c10.121584,-7.983727 22.617495,-7.426323 32.585466,1.450058c3.976349,3.537689 9.446106,13.880401 9.446106,17.857169c0,1.126297 0.900574,2.217712 2.00238,2.425995c1.802246,0.337784 1.560242,1.232925 -2.407043,8.890688c-2.424011,4.68338 -4.710968,8.849495 -5.074158,9.260567c-0.366791,0.411285 -2.836609,-3.42128 -5.49469,-8.513138l0,0z');
        refreshButtonShape.setAttribute('class', 'svg-pan-zoom-control-element');
        refreshButtonShape.setAttribute('id', 'rotateItem');
        refreshButtonShape.setAttribute('transform', 'rotate(0, 31.125, 31.08)');
        refreshButton.appendChild(refreshButtonShape);

        var svgContent = svgDom.getElementById('mnemonicSVG');
        svgContent.appendChild(refreshButton);

        var redirect = document.createElementNS('http://www.w3.org/2000/svg', 'g');
        redirect.setAttribute('id', 'redirect-button');
        redirect.setAttribute('class', 'svg-pan-zoom-control');
        redirect.setAttribute('transform', 'translate(' + (width - 5) + ' ' + 50 + ') scale(0.4)');
        redirect.setAttribute('onclick', 'top.redirectJSF()');

        var redirectPath = document.createElementNS('http://www.w3.org/2000/svg', 'path');
        redirectPath.setAttribute("d", "M69.05,58.1c-4.8,0-9.1,2.3-11.8,5.8l-24.3-14.1c1.5-3.7,1.5-7.8,0-11.5l24.3-14.1c2.7,3.5,7,5.8,11.8,5.8" +
            "c8.3,0,15-6.7,15-15s-6.7-15-15-15s-15,6.7-15,15c0,2,0.4,4,1.1,5.7l-24.3,14.2c-2.8-3.5-7-5.8-11.8-5.8c-8.3,0-15,6.7-15,15" +
            "s6.7,15,15,15c4.8,0,9.1-2.3,11.8-5.8l24.3,14.1c-0.7,1.7-1.1,3.7-1.1,5.7c0,8.3,6.7,15,15,15s15-6.7,15-15S77.35,58.1,69.05,58.1z" +
            " M69.05,4.1c6.1,0,11,4.9,11,11s-4.9,11-11,11c-6.1,0-11-4.9-11-11S62.95,4.1,69.05,4.1z M19.05,55.1c-6.1,0-11-4.9-11-11" +
            "s4.9-11,11-11s11,4.9,11,11S25.15,55.1,19.05,55.1z M69.05,84.1c-6.1,0-11-4.9-11-11s4.9-11,11-11c6.1,0,11,4.9,11,11" +
            "S75.15,84.1,69.05,84.1z");

        var redirectBackground = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
        redirectBackground.setAttribute('width', '85');
        redirectBackground.setAttribute('height', '85');
        redirectBackground.setAttribute('class', 'svg-pan-zoom-control-background');
        redirect.appendChild(redirectBackground);

        redirect.appendChild(redirectPath);

        var redirectTitle = document.createElementNS('http://www.w3.org/2000/svg', 'title');
        redirectTitle.appendChild(document.createTextNode('Мнемосхема сети'));
        redirect.appendChild(redirectTitle);

        svgContent.appendChild(redirect);

        loadData("load", null);
    }
});

function loadData(url, timer) {
    var svgObject = document.getElementById('svgDocument');
    if ('contentDocument' in svgObject) {
        var svgDom = svgObject.contentDocument;
    }

    var search = location.search;
    var objectId = {
        "objId" : search.substring(search.indexOf("objectId") + 9)
    };

    // Не работает в старых версиях chrome(44)
    // var urlParams = new URLSearchParams(location.search);
    // var objectId = {
    //     "objId" : urlParams.get("objectId")
    // };

    jQuery.ajax({
        url: url,
        method: "post",
        data: objectId,
        error: [
            function(message) {
                console.log(message.responseText);
            }
        ],
        success: [
            function(data) {
                for(var i in data) {
                    if (data.hasOwnProperty(i)) {
                        jQuery("#" + data[i].name, svgDom).changeVisible();
                        jQuery("#" + data[i].name, svgDom).text(data[i].data);

                        jQuery("#" + data[i].name + "_tit", svgDom).text(data[i].title);

                        jQuery("#" + data[i].name + "_col", svgDom).changeColor(data[i].color);

                        jQuery("#" + data[i].name + "_pic", svgDom).changeVisible();
                    }
                }

                if (timer != null) {
                    clearInterval(timer);
                }
            }
        ]
    });
}

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