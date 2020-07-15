# SignsControl
***
javadoc: connectionPoolForDataBase, rolesOrganisationsUsersController

vebView use: connectionPoolForDataBase, rolesOrganisationsUsersController, signsControl(pictures wasn't added), bank, orders
***
Основные идеи:
1. Контроль дорожных знаков. Карта, на карте точке, ткнуть на точку - получить информацию об актуальных знаках, которые расположены в этой точке + дополнение в виде информации о дорожных знаках, которые уже сняты, но когда-то тут висели. 
2. Заказчик(организация)/управляющий картой - может заказать дорожные знаки на конкретную точку. добавляет знаки на карту, меняет, удаляет 
3. Исполнитель (организация) - исполняет заказ - нажимает кнопку "выполнено". 
4. Банк (организация) - контролирует счета компаний (добавляет денег на счет, блокирует счета)

модули: connectionPoolForDataBase, rolesOrganisationsUsersController, signsControl, bank, orders, webView