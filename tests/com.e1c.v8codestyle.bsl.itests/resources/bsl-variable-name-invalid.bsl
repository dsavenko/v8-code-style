Процедура ТестПодчеркивания()
	Перем _ЯвнаяПеременнаяТестоваяПодчеркивание;
	_НеявнаяПеременнаяПодчеркивание = 2;
КонецПроцедуры

Процедура ТестЗаглавнаяБуква()
	Перем явнаяПеременнаяТестоваяНижнийРегистр;
	неявнаяПеременнаяНижнийРегистр = 2;
КонецПроцедуры

Процедура ТестМинимальнаяДлина()
	Перем ЯП;
	НП = 2;	
КонецПроцедуры

Процедура ТестКорректныеНаименования()	
	Перем ЯвнаяПеременнаяКорректноеНаименование;
	НеявнаяПеременнаяКорректноеНаименование = 2;
КонецПроцедуры

Процедура ТестИтераторы()
	МассивКонтрагентов = Новый Массив();
	Для й = 0 По МассивКонтрагентов.ВГраница() Цикл
		//Обработка массива
	КонецЦикла;
	
	Для Каждого Эл Из МассивКонтрагентов Цикл
		//Обработка массива
	КонецЦикла;
	
КонецПроцедуры

Процедура ТестИменаПараметровМетодов(Контрагенты)
КонецПроцедуры

Процедура ТестИменаПараметровМетодов2(_Контрагенты)
КонецПроцедуры