'use strict';

ymaps.ready(init);

function init() {

    let myMap = new ymaps.Map("map", {center: [53.90, 27.56], zoom: 12,
    controls: ['rulerControl','searchControl','typeSelector','zoomControl','geolocationControl','fullscreenControl']});

 // Обработка событий карты:
                                  // - boundschange - изменение границ области показа;
                                  // - type - изменение типа карты;
      myMap.events.add(['boundschange', 'typechange'], setLocationHash);

        setMapStateByHash();

          // Получение значение параметра name из адресной строки
                    // браузера.
                    function getParam (name, location) {
                        location = location || window.location.hash;
                        let res = location.match(new RegExp('[#&]' + name + '=([^&]*)', 'i'));
                        return (res && res[1] ? res[1] : false);
                    }

                    // Передача параметров, описывающих состояние карты,
                    // в адресную строку браузера.
                    function setLocationHash () {
                        let params = [
                            'type=' + myMap.getType().split('#')[1],
                            'center=' + myMap.getCenter(),
                            'zoom=' + myMap.getZoom()
                        ];

                        window.location.hash = params.join('&');
                    }

                    // Установка состояния карты в соответствии с переданными в адресной строке
                    // браузера параметрами.
                    function setMapStateByHash () {
                        let hashType = getParam('type'),
                            hashCenter = getParam('center'),
                            hashZoom = getParam('zoom');

                        if (hashType) {
                            myMap.setType('yandex#' + hashType);
                        }
                        if (hashCenter) {
                            myMap.setCenter(hashCenter.split(','));
                        }
                        if (hashZoom) {
                            myMap.setZoom(hashZoom);
                        }

                    }

}