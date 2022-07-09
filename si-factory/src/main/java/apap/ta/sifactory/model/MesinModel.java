package apap.ta.sifactory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "mesin")
public class MesinModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_mesin;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDate tanggal_dibuat;

    @NotNull
    @Column(name = "kapasitas", nullable = false)
    private Integer kapasitas;

    // id_kategori
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_kategori", referencedColumnName = "id_kategori")
    private KategoriModel kategori;

    // Relasi dengan produksi
    @OneToMany(mappedBy = "mesin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProduksiModel> listProduksi;

}
