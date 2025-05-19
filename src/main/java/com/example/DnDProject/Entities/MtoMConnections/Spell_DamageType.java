package com.example.DnDProject.Entities.MtoMConnections;

import com.example.DnDProject.Entities.Attribute.Attribute;
import com.example.DnDProject.Entities.Item.Item;
import com.example.DnDProject.Entities.Monster.DamageType.DamageType;
import com.example.DnDProject.Entities.Race.Race;
import com.example.DnDProject.Entities.Spell.Spell;
import jakarta.persistence.*;

@Entity
@Table(name = "spell_damage_type")
public class Spell_DamageType {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private int id;

    @Column(name = "damage_dice")
    private String damageDice;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "spell_name")
    private Spell spell;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }
}
