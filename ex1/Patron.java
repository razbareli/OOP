/**
 * This class represents a library patron that has a name and assigns values to
 * different literary aspects of books.
 */
class Patron {

    /**
     * first name of the patron
     */
    private final String firstName;
    /**
     * last name of the patron
     */
    private final String lastName;
    /**
     * comic vale of the patron
     */
    private final int comicValue;
    /**
     * dramatic value of the patron
     */
    private final int dramaticValue;
    /**
     * educational value of the paron
     */
    private final int educationalValue;
    /**
     * the minimum value in which the patron will enjoy a book
     */
    private final int enjoymentValue;

    /**
     * creates a patron with the following characteristics:
     * @param patronFirstName
     * @param patronLastName
     * @param comicTendency
     * @param dramaticTendency
     * @param educationalTendency
     * @param patronEnjoymentThreshold
     */
    Patron (String patronFirstName, String patronLastName,
            int comicTendency, int dramaticTendency, int educationalTendency,
            int patronEnjoymentThreshold){
        firstName = patronFirstName;
        lastName = patronLastName;
        comicValue = comicTendency;
        dramaticValue = dramaticTendency;
        educationalValue = educationalTendency;
        enjoymentValue = patronEnjoymentThreshold;
    }

    /**
     * prints the full name of the patron
     * @return full name of patron
     */
    String stringRepresentation(){
        return firstName + " " + lastName;
    }

    /**
     * Calculate the literary value this patron assigns to the given book,
     * based on the patron literary aspect weights.
     * @param book - the book to check
     * @return The literary value this patron assigns to the given book.
     */
    int getBookScore(Book book){
        return comicValue * book.comicValue
                + dramaticValue * book.dramaticValue
                + educationalValue * book.educationalValue;
    }

    /**
     * Returns true of this patron will enjoy the given book, false otherwise.
     * @param book The book to assess.
     * @return True of this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book){
        return getBookScore(book) >= enjoymentValue;
    }


}
