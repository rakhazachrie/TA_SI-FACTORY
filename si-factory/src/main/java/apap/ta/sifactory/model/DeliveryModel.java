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
public class DeliveryModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_delivery")
    private Integer idDelivery;

    @NotNull
    @Column(name="tanggal_dibuat", nullable = false)
    private LocalDate tanggal_dibuat;

    @NotNull
    @Column(name="tanggal_dikirim", nullable = false)
    private LocalDate tanggal_dikirim;

    @NotNull
    @Column(name="sent", nullable = false)
    private Boolean sent;

    // relasi dengan pegawai (kurir)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_kurir", referencedColumnName = "id_pegawai", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PegawaiModel kurir;

    // Relasi dengan Request
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_request_update_item", referencedColumnName = "id_request_update_item")
    private RequestUpdateItemModel requestUpdateItem;

}
