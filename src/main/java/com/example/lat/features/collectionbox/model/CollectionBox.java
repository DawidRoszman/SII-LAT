package com.example.lat.features.collectionbox.model;

import com.example.lat.features.fundraisingevent.model.FundraisingEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CollectionBox {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne FundraisingEvent fundraisingEvent;

    @OneToMany List<DonationCurrency> donations = new ArrayList<>();
}
