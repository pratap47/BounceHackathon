package test.com.apnigaadi;

public class otheruserinfo {

    int _id;
    String _name;
    String _phone_number;
    String UID;

    public otheruserinfo(){}
    public otheruserinfo(int id, String name, String _phone_number, String UID){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
        this.UID=UID;
    }
    public otheruserinfo(String name, String _phone_number, String UID){
        this._name = name;
        this._phone_number = _phone_number;
        this.UID=UID;

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }



}
