import java.util.*

fun main(args: Array<String>) {
    val car = Vehicle("AA111AA", VehicleType.CAR, "yes")
    val moto = Vehicle("BB222BB", VehicleType.MOTORCYCLE)
    val minibus = Vehicle("CC333CC", VehicleType.MINIBUS)
    val bus = Vehicle("DD444DD", VehicleType.BUS, "yes")
    val car2 = Vehicle("AA111AA", VehicleType.CAR)

    val parking = Parking(mutableSetOf(car, moto, minibus, bus))

    val isCar2Inserted = parking.vehicles.add(car2)


    println(parking.vehicles.contains(car))
    println(parking.vehicles.contains(moto))
    println(parking.vehicles.contains(minibus))
    println(parking.vehicles.contains(bus))
    println(isCar2Inserted)

}


// exercício 1:
// P: Por que vehicles é definido como um Set?
// R: Como queremos que as plates, que serão adicionadas a nossa Collection sejam
// unicas o Set não permitirá valores que se repitam.
data class Parking(val vehicles: MutableSet<Vehicle>) {
}


// exercício 2:
enum class VehicleType(val initialFee: Int) {
    CAR(20),
    MOTORCYCLE(15),
    MINIBUS(25),
    BUS(30),
}


// exercício 3:
// P: Onde as propriedades devem ser adicionadas, em ParkingSpace, Vehicle ou ambos?
// R: Em Vehicle!
// Já que checkInTime será registrado no momento que um Vehicle é instanciado e
// discountCard é opcional, existindo em alguns veículos e em outros não.
// P: Como indicamos que um tipo de dados pode adquirir essa característica no Kotlin?
// R: Elvis operator = ? null safety
data class Vehicle(
    val plate: String,
    val type: VehicleType,
    val discountCard: String? = null,
    val checkInTime: Calendar? = Calendar.getInstance(),
    ) {

    override fun equals(other: Any?): Boolean {
        if (other is Vehicle) {
            return this.plate == other.plate
        }

        return super.equals(other)
    }

    override fun hashCode(): Int = this.plate.hashCode()
}

// exercício 4:
// ??????onde colocar??
//const val MINUTES_IN_MILISECONDS = 60000
//val parkedTime: Long
//    get() = (Calendar.getInstance().timeInMillis - checkInTime.timeInMillis) / MINUTES_IN_MILISECONDS

class Constants {
    companion object {
        const val MINUTES_IN_MILISECONDS = 60000
    }
}
data class ParkingSpace(var vehicle: Vehicle) {
//    fun getParkedTime() {
        val constants = Constants
//        val parkedTime: Long = ((Calendar.getInstance().timeInMillis - (vehicle.checkInTime?.timeInMillis?.div(constants.MINUTES_IN_MILISECONDS))
//            ?.toLong() ?: Long )))
//    }


    private fun getParkedTime() =
    ((Calendar.getInstance().timeInMillis - (vehicle.checkInTime?.timeInMillis ?: Long)) / constants.MINUTES_IN_MILLISECONDS).toLong()
}
