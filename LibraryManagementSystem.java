
interface LibraryItem {
    public void borrowItem();
    public void returnItem();
    public boolean isBorrowed();
} 

class Book implements LibraryItem {
    public String bookTitle;
    public String author;
    public int year;
    private boolean borrowed;

    public Book(String bookTitle, String author, int year) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.year = year;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("Book - " + bookTitle + " is borrowed.");
        } else {
            System.out.println("Oops! Book - " + bookTitle + " is ALREADY borrowed!");
        }
    }

    @Override
    public void returnItem() {
        if (!borrowed) {
            borrowed = false;
            System.out.println("Book - " + bookTitle + " is returned.");
        } else {
            System.out.println("Book - " + bookTitle + " is not borrowed.");
        }
    }

    @Override
    public String toString() {
        return "Book - " + bookTitle + " by " + author + " (" + year + ")";
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }
}



class DVD implements LibraryItem {
    public String dvdTitle;
    public String director;
    public int dvdYear;
    private boolean borrowed;

    public DVD(String dvdTitle, String director, int dvdYear) {
        this.dvdTitle = dvdTitle;
        this.director = director;
        this.dvdYear = dvdYear;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("DVD - " + dvdTitle + " is borrowed.");
        } else {
            System.out.println("Oops! DVD - " + dvdTitle + " is ALREADY borrowed!");
        }
    }

    @Override
    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("DVD - " + dvdTitle + " is returned.");
        } else {
            System.out.println("DVD - " + dvdTitle + " is available.");
        }
    }

    @Override
    public String toString() {
        return "DVD - " + dvdTitle + " directed by " + director + " (" + dvdYear + ")";
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }
}

abstract class LibraryUser {
    abstract void borrowItem(LibraryItem item);
    abstract void returnItem(LibraryItem item);
    abstract void printItemsBorrowed();
}

class Student extends LibraryUser {
    private String name;
    private int id;
    private LibraryItem[] borrowedItems = new LibraryItem[5];
    private int borrowedItemCount = 0;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (borrowedItemCount < 5) {
            item.borrowItem();
            borrowedItems[borrowedItemCount++] = item;
        } else {
            System.out.println("Cannot borrow more items. Limit reached.");
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        item.returnItem();
        for (int i = 0; i < borrowedItemCount; i++) {
            if (borrowedItems[i] == item) {
                borrowedItems[i] = borrowedItems[borrowedItemCount - 1];
                borrowedItems[borrowedItemCount - 1] = null;
                borrowedItemCount--;
                break;
            }
        }
    }

    @Override
    public void printItemsBorrowed() {
        System.out.println("Student " + name + " has borrowed:");
        for (int i = 0; i < borrowedItemCount; i++) {
            System.out.println(borrowedItems[i]);
        }
    }
}

class Teacher extends LibraryUser {
    private String name;
    private int id;
    private LibraryItem[] borrowedItems = new LibraryItem[10];
    private int borrowedItemCount = 0;

    public Teacher(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (borrowedItemCount < 10) {
            item.borrowItem();
            borrowedItems[borrowedItemCount++] = item;
        } else {
            System.out.println("Cannot borrow more items. Limit reached.");
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        item.returnItem();
        for (int i = 0; i < borrowedItemCount; i++) {
            if (borrowedItems[i] == item) {
                borrowedItems[i] = borrowedItems[borrowedItemCount - 1];
                borrowedItems[borrowedItemCount - 1] = null;
                borrowedItemCount--;
                break;
            }
        }
    }

    @Override
    public void printItemsBorrowed() {
        System.out.println("Teacher " + name + " has borrowed:");
        for (int i = 0; i < borrowedItemCount; i++) {
            System.out.println(borrowedItems[i]);
        }
    }
}


class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Crazy Rich Asians", "Kevin Kwan",2013);
        Book book2 = new Book("No Longer Human", "Osamu Dazai", 1948);

        DVD dvd1 = new DVD("Se7en", "David Fincher", 1995);
        DVD dvd2 = new DVD("Shutter Island", "Martin Scorsese", 2010);

        Student student = new Student("Christian", 59290);
        Student student2 = new Student("Rey", 54950);
        Teacher teacher = new Teacher("Pontillas", 52470);

        System.out.println("\n//=============// XU LIBRARY SYSTEM //===============//");
        System.out.println("SYSTEM LOGS...");
        student.borrowItem(book1);
        teacher.borrowItem(dvd2);
        student2.borrowItem(book2);
        System.out.println("==============================================");
        student.printItemsBorrowed();
        System.out.println("==============================================");
        student2.printItemsBorrowed();
        System.out.println("==============================================");
        teacher.printItemsBorrowed();
        System.out.println("==============================================");
    }
}
