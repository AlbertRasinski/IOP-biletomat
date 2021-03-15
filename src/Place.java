public class Place {
    enum Status{
        taken, free, picked
    }

    private Status status;

    public Place(){
        status = Status.free;
    }

    public void setTaken(){
        status = Status.taken;
    }

    public Status getStatus(){
        switch (status){
            case taken:
                break;
            case free:
                break;
            case picked:
                break;
        }
        return status;
    }

    public Status pickPlace(){
        switch (status){
            case taken:
                break;
            case free:
                status = Status.picked;
                break;
            case picked:
                status = Status.free;
                break;
        }
        return status;
    }
}
