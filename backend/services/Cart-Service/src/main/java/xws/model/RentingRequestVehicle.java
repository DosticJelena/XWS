package xws.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@JsonSerialize
public class RentingRequestVehicle {
    @EmbeddedId
    private RentingRequestVehicleId id = new RentingRequestVehicleId();

    @JsonManagedReference
    @ManyToOne
    @MapsId("vehicle_id")
    private VehicleCart vehicle;

    @JsonManagedReference
    @ManyToOne
    @MapsId("renting_request_id")
    private RentingRequest rentingRequest;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private double price;

    @Column
    private double distance;

    @Embeddable
    @Getter
    @Setter
    public static class RentingRequestVehicleId implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long vehicle_id;
        private Long renting_request_id;

        public RentingRequestVehicleId() {

        }

        public RentingRequestVehicleId(Long vehicle_id, Long rentingRequest_id) {
            super();
            this.vehicle_id = vehicle_id;
            this.renting_request_id = rentingRequest_id;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((vehicle_id == null) ? 0 : vehicle_id.hashCode());
            result = prime * result
                    + ((renting_request_id == null) ? 0 : renting_request_id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            RentingRequestVehicleId other = (RentingRequestVehicleId) obj;
            return Objects.equals(getRenting_request_id(), other.getRenting_request_id()) && Objects.equals(getVehicle_id(), other.getVehicle_id());
        }
    }
}
