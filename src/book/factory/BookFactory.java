package book.factory;

import book.EnglishLanguage;
import book.RussianLanguage;

public interface BookFactory {

    RussianLanguage createRussianLiterature();

    EnglishLanguage createEnglishLiterature();
}
