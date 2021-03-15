public class MainGuiSystem {
    private static WindowPickOption pickOption;
    private static WindowPickCinema pickCinema;
    private static WindowPickMovie pickMovie;
    private static WindowPickDate pickDate;
    private static WindowPickPlace pickPlace;
    private static WindowSummary summary;
    private static WindowLogin login;
    private static WindowPickTicket ticket;
    private Database database;

    public MainGuiSystem(){
        database = new Database();
        pickDate = new WindowPickDate();
        pickOption = new WindowPickOption();
        pickCinema = new WindowPickCinema();
        pickMovie = new WindowPickMovie();
        pickPlace = new WindowPickPlace();
        summary = new WindowSummary();
        login = new WindowLogin();
        ticket = new WindowPickTicket();

        pickOption.setWindow();
    }

    public static void changeWindow(int value){
        switch (value){
            case 0:
                pickOption.setWindow();
                break;
            case 1:
                login.setWindow();
                break;
            case 2:
                pickCinema.setWindow();
                break;
            case 3:
                pickMovie.setWindow();
                break;
            case 4:
                pickDate.setWindow();
                break;
            case 5:
                pickPlace.setWindow();
                break;
            case 6:
                summary.setWindow();
                break;
            case 7:
                ticket.setWindow();
                break;
        }
    }
}
