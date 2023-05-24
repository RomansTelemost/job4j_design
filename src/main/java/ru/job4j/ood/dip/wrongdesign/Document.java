package ru.job4j.ood.dip.wrongdesign;

public class Document {

    private int id;
    private String name;
    private byte[] data;
    private Signer signer;

    public Signer getSigner() {
        return signer;
    }

    public void setSigner(Signer signer) {
        this.signer = signer;
    }
}
