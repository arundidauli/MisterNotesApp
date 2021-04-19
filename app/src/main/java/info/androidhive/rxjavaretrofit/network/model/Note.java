package info.androidhive.rxjavaretrofit.network.model;

/**
 * Created by ravi on 20/02/18.
 */

public class Note extends BaseResponse {
    String id;
    String note;
    String timestamp;

    public Note() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
