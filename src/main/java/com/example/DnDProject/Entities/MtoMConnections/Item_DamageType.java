package com.example.DnDProject.Entities.MtoMConnections;


import com.example.DnDProject.Entities.Attribute.Attribute;
import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.DamageType.DamageType;
import com.example.DnDProject.Entities.Race.Race;
import jakarta.persistence.*;

@Entity
@Table(name = "item_damage_type")
public class Item_DamageType {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private int id;


    @Column(name = "damage_dice")
    private String damageDice;

    @ManyToOne
    @JoinColumn(name = "item_name")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "damage_type_name")
    private DamageType damageType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDamageDice() {
        return damageDice;
    }

    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }
}
