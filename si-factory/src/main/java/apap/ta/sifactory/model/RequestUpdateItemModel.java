package apap.ta.sifactory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "request_update_item")
public class RequestUpdateItemModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_request_update_item;

    // id item
    @Column(name = "id_item", nullable = false)
    private String id_item;

    // id kategori
    @NotNull
    @Column(name = "id_kategori", nullable = false)
    private Integer id_kategori;

    @NotNull
    @Column(name = "tambahan_stok", nullable = false)
    private Integer tambahan_stok;

    @NotNull
    @Column(name = "tanggal_request", nullable = false)
    private LocalDate tanggal_request;

    // id cabang
    @NotNull
    @Column(name = "id_cabang", nullable = false)
    private Integer id_cabang;

    @NotNull
    @Column(name = "executed", nullable = false)
    private Boolean executed;

    // Relasi dengan Produksi
    @OneToOne(mappedBy = "requestUpdateItem")
    private ProduksiModel produksi;

    // Relasi dengan Delivery
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_delivery", referencedColumnName = "id_delivery")
    private DeliveryModel delivery;

}
