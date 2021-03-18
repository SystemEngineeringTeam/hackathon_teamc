package api;

public class TFResBody {
    public Boolean available = Boolean.FALSE;

    public void setAvailable(String vlbl) {
        if(vlbl.equals("1")){
            available = Boolean.TRUE;
        }
    }
}
