package br.com.brizidiolauro.oficinaaula01.data.model;

public class Pedido {
    private String name;
    private Double value;

    public Pedido(String name, Double value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
