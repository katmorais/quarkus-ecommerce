
package br.unitins.topicos2.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Checkout extends DefaultEntity{
    private String address;
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_checkout")
    private List<Camiseta> camisasIds;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public List<Camiseta> getCamisasIds() {
        return camisasIds;
    }

    public void setCamisasIds(List<Camiseta> camisasIds) {
        this.camisasIds = camisasIds;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
