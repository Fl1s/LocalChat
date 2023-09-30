# LowBudgetChat
Что-то типо пет проекта. В будущем буду рисовать ему нормальное GUI, а то чувствуется вайб 2000-х.

ЗАПУСК СЕРВЕРА и КЛИЕНТА! :
1.	Откройте класс Server и запустите его. 
2.	Порт любой кроме 0. Например, 1000. Если получите ответ «порт занят», пробуйте любое другое число. Если «Чат сервер запущен.», то все ОК.
3.	Откройте класс ClientGuiController или Client и запустите. 
4.	Адрес всегда - 127.0.0.1
5.	Порт – тот, который вы определили для сервера. Например, 7777 или 4423. Желательно от 
6.	Теперь у вас запущен сервер и приложение. Можете запустить так же бота.

ЗАПУСК СЕРВЕРА / КЛИЕНТА В ЛОКАЛЬНОЙ СЕТИ

1.	Вам надо использовать метод ServerSocket(int port, int backlog, InetAddress bindAddr), где: 

port – вы получаете из консоли (не меняем код, если не хотите);

backlog – макс количество соединений с сервером (я поставил 10);

bindAddr – это адрес вашего компа в локальной сети. Чтобы проверить его, открываем командную строку (cmd в windows), вводим команду ipconfig и ищем строку IPv4 Address, в которой будет что-то вроде: 
IPv4 Address. . . . . . . . . . . : 192.168.178.22

2.	В методе main класса Server в строке, где создается new ServerSocket в параметры передаем не только порт, а все три параметра:
try (ServerSocket serverSocket = new ServerSocket (port, 10, InetAddress.getByName("192.168.178.22")))

IP обязательно через InetAdress.getByName. 
Теперь сервер будет запускаться не локально на компьютере, а в локальной сети. 

3.	Теперь необходимо открыть порт в брендмауре/антивирусе для локальной сети. Вам придется гуглить, как это сделать. В моем случае (ESET NOD32) это было:  
Антивирус -> Настройки -> Защита Сети -> Файервол -> Дополнительно -> Правила -> Изменить -> Добавить  -> {
Направление: оба
Протокол: TCP и UDP
Локальные -> Порт: 1000 //Указываем порт, который хотим открыть}  -> Принять

После этого запросы на ваш локальный IP через этот порт будут нормально обрабатываться вашим сервером. 

4.	Проходим все 6 пунктов из примера выше «ЗАПУСК СЕРВЕРА / КЛИЕНТА». Только теперь указываем порт, который открыли (в моем примере – это 1000) и ваш локальный IP (в моем примере – это 192.168.178.22). Все, можно чатиться в локальной сети

То же самое можно сделать и для интернет. Только вам нужен статический (физический) IP, который даст вам ваш интернет провайдер. Проверить это можно у провайдера, либо методом тыка. Соответственно вам надо с этим IP и открытым для него портом сделать все по шагам выше.

ЗАПУСКАЕМЫЙ КЛИЕНТ БЕЗ INTELLIJ IDEA

1.	Для начала надо сделать jar file. 

2.	Теперь надо сделать билд. 
Build -> Build Artifact -> Build

3.	Ваш jar файл будет расположен тут: …\LowBudgetChat\out\artifacts\ 
(В моем случае полный адрес: C:\JetBrains\Projects\LowBudgetChat\out\artifacts\)

4.	Заходим в папку, где располагается ваш jar файл. Можно переименовать его, к примеру в Chat.jar 

5.	Создаем тут же текстовый файл. Называем его, к примеру, Chat.txt. Открываем и делаем запись в одну строку: start javaw -jar Chat.jar

* Chat.jar – это имя вашего jar файла.

6.	Сохраняем и закрываем файл. 

7.	Переименовываем txt файл в Chat.bat. Важно, чтобы изменилось расширение файла с txt на bat. Если у вас 11 винда, то вам надо в этой папке нажать view -> show -> file view extensions. И после этого изменить расширение файла с txt на bat. 

8.	Готово. Запускайте bat файл. Теперь вы можете переместить файлы jar + bat куда угодно и запускать без IDEA. 
