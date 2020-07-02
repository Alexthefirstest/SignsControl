'use strict';
let myMap;
let script;
let pointAjaxUrl = ctx + "/get_current_points";
let pointAjaxDate = "1999-07-21";
let signsDiv;
let objectManager;
let indicatePointCoordinates = null;
let indicatePlacemark;
let coordinatesHtml;
let point_form;
   let objectManagerEP;

function init(ymaps) {

    myMap = new ymaps.Map("map", {
        center: [53.90, 27.56], zoom: 12,
        controls: ['rulerControl', 'searchControl', 'typeSelector', 'zoomControl', 'geolocationControl', 'fullscreenControl']
    });


    //---------поиск на карте - начало, добавляет организации - удалить из массива сверху searchControl, работает, мб не нужен
    // let searchControl = new ymaps.control.SearchControl({
    //     options: {
    //         provider: 'yandex#search'
    //     }
    // });
    //
    // myMap.controls.add(searchControl);
    //---------поиск на карте - конец

    //-----------------------------сохранение положения карты ---- начало

    // Обработка событий карты:
    // - boundschange - изменение границ области показа;
    // - type - изменение типа карты;
    myMap.events.add(['boundschange', 'typechange'], setLocationHash);

    setMapStateByHash();

    // Получение значение параметра name из адресной строки
    // браузера.
    function getParam(name, location) {
        location = location || window.location.hash;
        let res = location.match(new RegExp('[#&]' + name + '=([^&]*)', 'i'));
        return (res && res[1] ? res[1] : false);
    }

    // Передача параметров, описывающих состояние карты,
    // в адресную строку браузера.
    function setLocationHash() {
        let params = [
            'type=' + myMap.getType().split('#')[1],
            'center=' + myMap.getCenter(),
            'zoom=' + myMap.getZoom()
        ];

        window.location.hash = params.join('&');
    }

    // Установка состояния карты в соответствии с переданными в адресной строке
    // браузера параметрами.
    function setMapStateByHash() {
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

    // ----------------------- сохранение положения карты - конец

    // ----------------------- список городов - начало

    let ListBoxLayout = ymaps.templateLayoutFactory.createClass(
        "<button id='my-listbox-header' class='btn btn-success dropdown-toggle' data-toggle='dropdown'>" +
        "{{data.title}} <span class='caret'></span>" +
        "</button>" +
        // Этот элемент будет служить контейнером для элементов списка.
        // В зависимости от того, свернут или развернут список, этот контейнер будет
        // скрываться или показываться вместе с дочерними элементами.
        "<ul id='my-listbox'" +
        " class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu'" +
        " style='display: {% if state.expanded %}block{% else %}none{% endif %};'></ul>", {

                build: function () {
                    // Вызываем метод build родительского класса перед выполнением
                    // дополнительных действий.
                    ListBoxLayout.superclass.build.call(this);

                    this.childContainerElement = $('#my-listbox').get(0);
                    // Генерируем специальное событие, оповещающее элемент управления
                    // о смене контейнера дочерних элементов.
                    this.events.fire('childcontainerchange', {
                        newChildContainerElement: this.childContainerElement,
                        oldChildContainerElement: null
                    });
                },

                // Переопределяем интерфейсный метод, возвращающий ссылку на
                // контейнер дочерних элементов.
                getChildContainerElement: function () {
                    return this.childContainerElement;
                },

                clear: function () {
                    // Заставим элемент управления перед очисткой макета
                    // откреплять дочерние элементы от родительского.
                    // Это защитит нас от неожиданных ошибок,
                    // связанных с уничтожением dom-элементов в ранних версиях ie.
                    this.events.fire('childcontainerchange', {
                        newChildContainerElement: null,
                        oldChildContainerElement: this.childContainerElement
                    });
                    this.childContainerElement = null;
                    // Вызываем метод clear родительского класса после выполнения
                    // дополнительных действий.
                    ListBoxLayout.superclass.clear.call(this);
                }
            }),

        // Также создадим макет для отдельного элемента списка.
        ListBoxItemLayout = ymaps.templateLayoutFactory.createClass(
            "<li><a>{{data.content}}</a></li>"
        ),

        // Создадим  пункта выпадающего списка
        listBoxItems = [
            new ymaps.control.ListBoxItem({
                data: {
                    content: 'Минск',
                    center: [53.90, 27.56],
                    zoom: 12
                }
            }), new ymaps.control.ListBoxItem({
                data: {
                    content: 'Брест',
                    center: [52.09776408529675, 23.69610453788942],
                    zoom: 12
                }
            }), new ymaps.control.ListBoxItem({
                data: {
                    content: 'Витебск',
                    center: [55.1926727351534, 30.206306926835314],
                    zoom: 12
                }
            }), new ymaps.control.ListBoxItem({
                data: {
                    content: 'Гомель',
                    center: [52.42510947995823, 31.009052120149114],
                    zoom: 12
                }
            }), new ymaps.control.ListBoxItem({
                data: {
                    content: 'Гродно',
                    center: [53.678083189470364, 23.830716072310775],
                    zoom: 12
                }
            }),
            new ymaps.control.ListBoxItem({
                data: {
                    content: 'Могилёв',
                    center: [53.898049170273595, 30.332562737166853],
                    zoom: 12
                }
            })
        ],

        // Теперь создадим список.
        listBox = new ymaps.control.ListBox({
            items: listBoxItems,
            data: {
                title: 'Областные центры'
            },
            options: {
                // С помощью опций можно задать как макет непосредственно для списка,
                layout: ListBoxLayout,
                // так и макет для дочерних элементов списка. Для задания опций дочерних
                // элементов через родительский элемент необходимо добавлять префикс
                // 'item' к названиям опций.
                itemLayout: ListBoxItemLayout
            }
        });

    listBox.events.add('click', function (e) {
        // Получаем ссылку на объект, по которому кликнули.
        // События элементов списка пропагируются
        // и их можно слушать на родительском элементе.
        let item = e.get('target');
        // Клик на заголовке выпадающего списка обрабатывать не надо.
        if (item != listBox) {
            myMap.setCenter(
                item.data.get('center'),
                item.data.get('zoom')
            );
        }
    });

    myMap.controls.add(listBox, {float: 'left'});

    // ----------------------- список городов - конец


    //---------------------метки начаоло

    objectManager = new ymaps.ObjectManager({
        clusterize: true
    });

    objectManager.objects.options.set('preset', 'islands#violetCircleIcon'); //размеры сюда
    objectManager.clusters.options.set('preset', 'islands#violetClusterIcons');

 objectManagerEP = new ymaps.ObjectManager({
                       clusterize: true
                   });

                    objectManagerEP.objects.options.set('preset', 'islands#redCircleIcon'); //размеры сюда
                                      objectManagerEP.clusters.options.set('preset', 'islands#redClusterIcons');

  myMap.geoObjects.add(objectManager);
   myMap.geoObjects.add(objectManagerEP);

 myMap.events.add('dblclick', function (e) {

      objectManager.removeAll();

      });

//indicate point
  if( indicatePointCoordinates!=null) {

 indicatePlacemark = new ymaps.Placemark(indicatePointCoordinates,{}, {preset: "islands#redIcon", draggable: "true"});

   indicatePlacemark.events.add("dragend", function (e) {


//alert(e.get('coords'));



 coordinatesHtml.value=indicatePlacemark.geometry.getCoordinates();



   $.ajax({

          url: ctx+"/get_unused_directions",
data: {"pointCoordinates": coordinatesHtml}

      }).done(function (data) {

let directions = JSON.parse(data)

let directionsSelect=document.getElementById("direction");

 $("#direction").empty();

for(let j = 0; j<directions.ids.length; j++){

  directionsSelect.append(new Option(directions.directions[j], directions.ids[j]));
}

  }).fail(function() {

        $("#direction").empty();
        //exception

      });
//ajax/

              });


  myMap.geoObjects.add(indicatePlacemark);


  }
//---indicate point
//// Поставим метку по клику над картой.
//map.events.add('click', function (e) {
//    // Географические координаты точки клика можно узнать
//    // посредством вызова метода .get('coords').
//    var position = e.get('coords');
//    map.geoObjects.add(new ymaps.Placemark(position));
//});

//---координаты щелчка myMap.events ВЫДАЕТ КООРДИНАТЫ ПРИ КЛИКЕ НА ТОЧКУ - РАБОТАЕТ
//objectManager.events.add('click', function (e) {
//	let coords = e.get('coords');
//
//alert("ye")
//       alert(coords);
//    });

//-----------signs hisory

let activeObjectMonitor = new ymaps.Monitor(objectManager.clusters.state);

	objectManager.objects.events.add('click', function (e) {

			let objectId = e.get('objectId');
			updateSignsHistory(objectId);
			changeDirectionCoordinates(objectManager.objects.getById(objectId).properties.pointCoordinates);
			updateAddSignForm(objectManager.objects.getById(objectId).properties.pointCoordinates)

		});

		activeObjectMonitor.add('activeObject', function () {
        			let objectId = activeObjectMonitor.get('activeObject').id;
        		//	objectManager.objects.getById(objectId).properties.balloonContent;
        		updateSignsHistory(objectId);
        		//проверить что существует changeDIrectionCoorditnates
        		changeDirectionCoordinates(objectManager.objects.getById(objectId).properties.pointCoordinates);

	updateAddSignForm(objectManager.objects.getById(objectId).properties.pointCoordinates)
        		});

let activeObjectMonitorEP = new ymaps.Monitor(objectManagerEP.clusters.state);

	objectManagerEP.objects.events.add('click', function (e) {

			let objectId = e.get('objectId');

			changeDirectionCoordinates(objectManagerEP.objects.getById(objectId).properties.pointCoordinates);
		changeSignsHistoryDiv("empty point");
		updateAddSignForm(objectManagerEP.objects.getById(objectId).properties.pointCoordinates)
		});

		activeObjectMonitorEP.add('activeObject', function () {
        			let objectId = activeObjectMonitorEP.get('activeObject').id;
        		//	objectManager.objects.getById(objectId).properties.balloonContent;

        				changeDirectionCoordinates(objectManagerEP.objects.getById(objectId).properties.pointCoordinates);
        				 changeSignsHistoryDiv("empty point");

updateAddSignForm(objectManagerEP.objects.getById(objectId).properties.pointCoordinates)
        		});


function updateSignsHistory(objectId) {

if (document.getElementById("signsHistory").checked) {

  $.ajax({
        url: ctx+"/get_point_history",
        data: {"pointCoordinates": objectManager.objects.getById(objectId).properties.pointCoordinates}
    }).done(function (data) {
        changeSignsHistoryDiv(data);

    });
  }
 // changeSignsHistoryDiv(objectManager.objects.getById(objectId).properties.pointCoordinates);

}


myMap.balloon.events.add('userclose', function (e) {

changeSignsHistoryDiv("выберите точку");

});




    $.ajax({
        url: pointAjaxUrl,
        data: {"chosenDate": pointAjaxDate}
    }).done(function (data) {
        objectManager.add(data);
    });

    //   ------------- метки конец


}//init finish


// ----------------------- логализация - начало

window.onload = function () {

    // Получим ссылки на элементы с тегом 'head' и id 'language'.
    let head = document.getElementsByTagName('head')[0];
    let select = document.getElementById('language');
    select.createMap = function () {

        // Получим значение выбранного языка.
        let language = this.value;
        // Если карта уже была создана, то удалим её.
//        setTimeout(function () {if (myMap) {
//                                            myMap.destroy();
//                                        } }
//                                        , 1000);
        if (myMap) {
            myMap.destroy();
        }
        // Создадим элемент 'script'.
        script = document.createElement('script');
        script.type = 'text/javascript';
        script.charset = 'utf-8';
        // Запишем ссылку на JS API Яндекс.Карт с выбранным языком в атрибут 'src'.
        script.src = 'https://api-maps.yandex.ru/2.1/?onload=init_' + language + '&lang=' + language +
            '_RU&ns=ymaps_' + language;
        // Добавим элемент 'script' на страницу.
        head.appendChild(script);
        // Использование пространства имен позволяет избежать пересечения названий функций
        // и прочих программных компонентов.
        window['init_' + language] = function () {
            init(window['ymaps_' + language]);
        }


    };
    // Назначим обработчик для события выбора языка из списка.
    document.getElementById('language').addEventListener("change", select.createMap);
    // Создадим карту и зададим для нее язык, который был выбран по умолчанию.

//points listeners

     $(document).on("change", "#chosenDate", function () {


        pointAjaxDate = (document.getElementById('chosenDate')).value;

        pointAjaxUrl = ctx + "/get_points_by_date";

        select.createMap();

    });


    $(document).on("click", "#resetButton", function () {


        pointAjaxUrl = ctx + "/get_current_points";

        select.createMap();

    });

//points listeners fin

//signs hostory
    signsDiv = document.getElementById("signsHistoryTable");


 $(document).on("click", "#signsHistory", function(){

      changeSignsHistoryDiv("выберите точку");

    });


    select.createMap();//----create map here


    //add point

    if (document.getElementById("addPointButton")!=null) {

 point_form = document.getElementById("point_form");
    point_form.style.visibility = 'hidden';
coordinatesHtml=document.getElementsByName('coordinatesToSend')[0];


coordinatesHtml.value=0;
     $(document).on("click", "#addPointButton", function(){

 myMap.events.add('click', function (e) {


           indicatePointCoordinates = e.get('coords');
           coordinatesHtml.value=indicatePointCoordinates;
              point_form.style.visibility = 'visible';



   $.ajax({

          url: ctx+"/get_unused_directions",
data: {"pointCoordinates": coordinatesHtml.value}

      }).done(function (data) {

let directions = JSON.parse(data)

let directionsSelect=document.getElementById("direction");

 $("#direction").empty();

for(let j = 0; j<directions.ids.length; j++){

  directionsSelect.append(new Option(directions.directions[j], directions.ids[j]));
}

  }).fail(function() {

        $("#direction").empty();
        //exception

      });
//ajax/


                select.createMap();

            });

    });

     $(document).on("click", "#addDirectionForm", function(){

           changeDirectionCoordinates(null);

         });

 $(document).on("click", "#showEmptyPointsButton", function(){

 $.ajax({

        url: ctx+"/get_empty_points"

    }).done(function (data) {

    objectManagerEP.removeAll();

               objectManagerEP.add(data);
           });

    });
 }

  //add point/

updateAddSignForm(null);

}//window.onload;


function changeSignsHistoryDiv(html) {

    if (document.getElementById("signsHistory").checked) {
 signsDiv.innerHTML = html;
  signsDiv.style.visibility = 'visible';


    } else {

        signsDiv.style.visibility = 'hidden';
    }
//signs hostory fin
}

function changeDirectionCoordinates(coordinates) {

let addDirectionForm=document.getElementById("addDirectionForm");

    if((addDirectionForm!=null) && (addDirectionForm.checked)) {




if(coordinates!=null){

  coordinatesHtml.value=coordinates;

   $.ajax({

          url: ctx+"/get_unused_directions",
data: {"pointCoordinates": coordinates}

      }).done(function (data) {

let directions = JSON.parse(data)

let directionsSelect=document.getElementById("direction");

 $("#direction").empty();

for(let j = 0; j<directions.ids.length; j++){

  directionsSelect.append(new Option(directions.directions[j], directions.ids[j]));
}

  }).fail(function() {

        $("#direction").empty();
        //exception

      });

}
point_form.style.visibility = 'visible';

    } else {
if(point_form!=null){
         point_form.style.visibility = 'hidden';
  }
    }
//signs hostory fin
}

function  updateAddSignForm(coordinates){

let addSignCheckbox= document.getElementById("addSignCBox");
let addSignForm= document.getElementById("addSign_form");

if((addSignCheckbox!=null) && (addSignCheckbox.checked)){

  $.ajax({

          url: ctx+"/get_sign_add_info",
          data: {"pointCoordinates": coordinates}

      }).done(function (data) {

let addSignsInfo = JSON.parse(data)
let receivedSignsLists=addSignsInfo.signsLists;
let receivedPddSigns=addSignsInfo.pddSigns;
let receivedStandardSizes=addSignsInfo.standardSizes;

let signListSelect=document.getElementById("sign_list");
let pddSignSelect=document.getElementById("pdd_sign");
let standardSizeSelect=document.getElementById("standard_size");

 $("#sign_list").empty();
 $("#pdd_sign").empty();
 $("#standard_size").empty();

for(let k = 0; k<receivedSignsLists.length; k++){

  signListSelect.append(new Option(receivedSignsLists[k].direction, receivedSignsLists[k].id));

}

for(let k = 0; k<receivedPddSigns.length; k++){


  pddSignSelect.append(new Option(receivedPddSigns[k].sign, receivedPddSigns[k].id));

}for(let k = 0; k<receivedStandardSizes.length; k++){

  standardSizeSelect.append(new Option(receivedStandardSizes[k].size, receivedStandardSizes[k].size));
}

  }).fail(function() {

        $("#direction").empty();
        //exception

      });

addSignForm.style.visibility = 'visible';
}else{
if(addSignCheckbox!=null){
addSignForm.style.visibility = 'hidden';
}
}


}
// ----------------------- локализация - конец
