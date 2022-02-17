package com.revature.team4.beans.apiResponseDAO.propertiesList;

public class PropertyListDAO {
    private String result;
    private ListDataDAO data;

    public PropertyListDAO() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ListDataDAO getData() {
        return data;
    }

    public void setData(ListDataDAO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PropertyListDAO{" +
                "result='" + result + '\'' +
                ", data=" + data +
                '}';
    }
}
