/**
 * This class represents a library, which hold a collection of books.
 */
class Library {

    /**
     * max book that the library can have
     */
    private final int maxBookCapacity;
    /**
     * max books a patron can borrow
     */
    private final int maxBorrowedBooks;
    /**
     * max patrons that can be registered
     */
    private final int maxPatronCapacity;
    /**
     * books in the library
     */
    private Book[] books;
    /**
     * patrons registered in the library
     */
    private Patron[] patrons;
    /**
     * number of books in the library
     */
    private int booksSize = 0;
    /**
     * number of patrons registered
     */
    private int patronsSize = 0;

    /**
     * creates a library with the following characteristics:
     * @param maxBookCapacity
     * @param maxBorrowedBooks
     * @param maxPatronCapacity
     */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity){
        this.maxBookCapacity = maxBookCapacity;
        this.maxBorrowedBooks = maxBorrowedBooks;
        this.maxPatronCapacity = maxPatronCapacity;
        books = new Book[maxBookCapacity];
        patrons = new Patron[maxPatronCapacity];
    }

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     * @param book - The book to add to this library.
     * @return A non-negative id number for the book if there is a spot and the book was successfully added.
     * The current id of the book if already in the library. Otherwise, a negative number.
     */
    int addBookToLibrary(Book book){
        int temp = isBookInLibrary(book);
        if (temp != -1){
            return temp;
        }else{
            if (maxBookCapacity == booksSize){
                return -1;
            }else{
                books[booksSize] = book;
                booksSize++;
                return booksSize -1;
            }
        }
    }

    /**
     * Returns if the given number is an id of a book in the library.
     * @param bookId: The id to check.
     * @return True if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId){
        return 0 <= bookId && bookId <= booksSize -1;
    }

    /**
     * Returns the id number of the given book if it is owned by this library, -1 otherwise.
     * @param book The book for which to find the id number.
     * @return A non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book){
        return isBookInLibrary(book);
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     * @param bookId he id number of the book to check.
     * @return True if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId){
        return isBookIdValid(bookId) && books[bookId].currentBorrowerId == -1;
    }

    /**
     * Registers the given Patron to this library, if there is a spot available.
     * @param patron The patron to register to this library.
     * @return A non-negative id number for the patron if there is a spot and the patron was successfully registered.
     * The current id of the patron if already registered. Otherwise, a negative number.
     */
    int registerPatronToLibrary(Patron patron){
        int temp = isPatronInLibrary(patron);
        if (temp != -1){
            return temp;
        }else{
            if (maxPatronCapacity == patronsSize){
                return -1;
            }else{
                patrons[patronsSize] = patron;
                patronsSize++;
                return patronsSize -1;
            }
        }
    }

    /**
     * Returns if the given number is an id of a patron in the library.
     * @param patronId The id to check.
     * @return True if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId){
        return 0 <= patronId && patronId < patronsSize;
    }

    /**
     * Returns the id number of the given patron if he is
     * registered to this library, -1 otherwise.
     * @param patron The patron for which to find the id number.
     * @return A non-negative id number of the given patron
     * if he is registered to this library, -1 otherwise.
     */
    int getPatronId(Patron patron){
        return isPatronInLibrary(patron);
    }

    /**
     * Marks the book with the given id number as borrowed by
     * the patron with the given patron id,
     * if this book is available, the given patron isn't already borrowing the maximal number
     * of books allowed, and if the patron will enjoy this book.
     * @param bookId The id number of the book to borrow.
     * @param patronId The id number of the patron that will borrow the book.
     * @return True if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId){
        if (isBookAvailable(bookId) && isPatronIdValid(patronId)
            && patronNumOfBooks(patronId) < maxBorrowedBooks
            && patrons[patronId].willEnjoyBook(books[bookId]))
        {
            books[bookId].currentBorrowerId = patronId;
            return true;
        }
        return false;
    }

    /**
     * Return the given book.
     * @param bookId The id number of the book to return.
     */
    void returnBook(int bookId){
        if (isBookIdValid(bookId)) {
            books[bookId].currentBorrowerId = -1;
        }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most,
     * out of all available books he will enjoy, if any such exist.
     * @param patronId The id number of the patron to suggest the book to.
     * @return The available book the patron with the given will enjoy the most.
     * 'null' if no book is available.
     */
    Book suggestBookToPatron(int patronId){
        if (!isPatronIdValid(patronId)) {
            return null;
        }
        Book bestBook = null;
        int highest = -1;
        for (int i = 0; i < booksSize; i++) {
            if (isBookAvailable(i)){
                int currScore = patrons[patronId].getBookScore(books[i]);
                if (patrons[patronId].willEnjoyBook(books[i]) && currScore > highest){
                    highest = currScore;
                    bestBook = books[i];
                }
            }
        }
        return bestBook;
    }

    /**
     * returns the num of books the patron has
     * @param patronId
     * @return the num of books the patron has
     */
    private int patronNumOfBooks(int patronId){
        int ans = 0;
        for (int i=0; i<booksSize; i++){
            if (books[i].currentBorrowerId == patronId){
                ans++;
            }
        }
        return ans;
    }

    /**
     * checkes if the patron is registered in the library
     * @param patron
     * @return ID of the patron if he is registered, -1 if not.
     */
    private int isPatronInLibrary(Patron patron){
        for (int i=0 ;i < patronsSize; i++){
            if (patron == patrons[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * checks if the book is in the library
     * @param book
     * @return ID of the book if he is in the library, -1 if not.
     */
    private int isBookInLibrary(Book book){
        for (int i=0 ;i < booksSize; i++){
            if (book == books[i]) {
                return i;
            }
        }
        return -1;
    }
}
