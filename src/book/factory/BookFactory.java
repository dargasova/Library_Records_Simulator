package book.factory;

import book.educational.EducationalLiterature;
import book.fiction.FictionLiterature;

public interface BookFactory {

    EducationalLiterature createEnglishEducationalLiterature();

    EducationalLiterature createRussianEducationalLiterature();

    FictionLiterature createEnglishFictionLiterature();
    FictionLiterature createRussianFictionLiterature();
}
