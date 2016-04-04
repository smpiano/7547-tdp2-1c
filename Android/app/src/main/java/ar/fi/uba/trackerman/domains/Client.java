package ar.fi.uba.trackerman.domains;

/**
 * Created by plucadei on 29/3/16.
 */
public class Client {
    private long id;
    private String name;
    private String lastName;
    private String cuil;
    private String address;
    private String email;
    private double lon;
    private double lat;
    private String thumbnail;
    private String avatar;
    private String phoneNumber;

    public Client(long id) {
        super();
        this.id= id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setAddress(String address) { this.address = address; }

    public String getAddress() { return address; }

    public long getId() { return id; }

    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getAvatar() { return avatar; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
