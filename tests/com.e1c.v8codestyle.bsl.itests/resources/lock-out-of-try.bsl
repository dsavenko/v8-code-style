Процедура Тест()
    
    БлокировкаДанных = Новый БлокировкаДанных;
    ЭлементБлокировкиДанных = БлокировкаДанных.Добавить("Документ.ПриходнаяНакладная");
    ЭлементБлокировкиДанных.Режим = РежимБлокировкиДанных.Исключительный;
    БлокировкаДанных.Заблокировать();
    
КонецПроцедуры