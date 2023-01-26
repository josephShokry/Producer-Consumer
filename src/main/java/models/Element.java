package models;

import DTOs.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Element {
    private String id;
    private int x;
    private int y;
    private String color;
    private String text;

    public Element(Dto dto) {
        this.id = dto.id;
        this.x = dto.x;
        this.y = dto.y;
        this.color = Double.toString(Math.random() * 0x1000000);
        this.text = dto.text;

    }
}
