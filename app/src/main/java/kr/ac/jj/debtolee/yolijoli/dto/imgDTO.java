package kr.ac.jj.debtolee.yolijoli.dto;

public class imgDTO {
    String name;
    String partimg;
    String img;
    String date;

    public imgDTO(String name, String partimg, String img, String date) {
        this.name = name;
        this.partimg = partimg;
        this.img = img;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartimg() {
        return partimg;
    }

    public void setPartimg(String partimg) {
        this.partimg = partimg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
