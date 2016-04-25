package ar.fi.uba.trackerman.domains;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.fi.uba.trackerman.exceptions.OrderTrackerException;
import ar.fi.uba.trackerman.utils.DateUtils;
import ar.fi.uba.trackerman.utils.FieldValidator;

/**
 * Created by plucadei on 17/4/16.
 */
public class Order {

    private long id;
    private long clientId;
    private long vendorId;
    private Date deliveryDate;
    private Date dateCreated;
    private String status;
    private double totalPrice;
    private String currency;
    private List<OrderItem> orderItems;

    public Order(long id, long clientId, long vendorId, Date dateCreated, String status, double totalPrice, String currency){
        this.id=id;
        this.clientId= clientId;
        this.vendorId= vendorId;
        this.dateCreated = dateCreated;
        this.status= status;
        this.totalPrice= totalPrice;
        this.currency= currency;
        this.orderItems= new ArrayList<OrderItem>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
    }

    @Override
    public int hashCode() {
        return (int)(this.id % Integer.MAX_VALUE);
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Order){
            Order order= (Order) object;
            return this.id==order.id;
        }
        return false;
    }

    public static Order fromJson(JSONObject json) {
        Order order = null;
        try {
            long id = json.getLong("id");
            long vendorId = json.getLong("vendor_id");
            long clientId = json.getLong("client_id");
            String dateCreatedStr = json.getString("date_created");
            Date dateCreated = null;
            if (FieldValidator.isValid(dateCreatedStr)) dateCreated = DateUtils.parseDate(dateCreatedStr);
            String status = json.getString("status");
            double totalPrice = json.getDouble("total_price");
            String currency = json.getString("currency");
            order = new Order(id,clientId,vendorId,dateCreated,status,totalPrice,currency);

            String deliveryDateStr = json.getString("delivery_date");
            Date deliveryDate = null;
            if (deliveryDateStr != null && !"null".equalsIgnoreCase(deliveryDateStr)) deliveryDate = DateUtils.parseDate(deliveryDateStr);
            order.setDeliveryDate(deliveryDate);

            if (json.toString().contains("order_items")) {
                JSONArray itemsJson = json.getJSONArray("order_items");
                for (int i = 0; i < itemsJson.length(); i++) {
                    order.addOrderItem(OrderItem.fromJson(itemsJson.getJSONObject(i)));
                }
            }
        } catch (JSONException e) {
            throw new OrderTrackerException("Error parsing Order.",e);
        }
        return order;
    }
}
