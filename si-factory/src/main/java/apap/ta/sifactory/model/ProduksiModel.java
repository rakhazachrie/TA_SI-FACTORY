package apap.ta.sifactory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "produksi")
public class ProduksiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produksi;

    // id item
    @Column(name = "id_item", nullable = false)
    private String idItem;

    // id kategori
    @NotNull
    @Column(name = "id_kategori", nullable = false)
    private Integer id_kategori;

    @NotNull
    @Column(name = "tambahan_stok", nullable = false)
    private Integer tambahan_stok;

    @NotNull
    @Column(name = "tanggal_produksi", nullable = false)
    private LocalDate tanggal_produksi;

    // relasi dengan pegawai
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pegawai", referencedColumnName = "id_pegawai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PegawaiModel pegawai;

    // Relasi dengan Mesin
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_mesin", referencedColumnName = "id_mesin", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MesinModel mesin;

    // Relasi dengan Request
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_request_update_item", referencedColumnName = "id_request_update_item")
    private RequestUpdateItemModel requestUpdateItem;

}
