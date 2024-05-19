package hr.tvz.android.listaloknerladjevic

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import hr.tvz.android.listaloknerladjevic.databinding.ActivityCarListBinding

class CarListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarListBinding
    private lateinit var carAdapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadCars()
    }

    private fun setupRecyclerView() {
        carAdapter = CarAdapter(emptyList()) { car ->
            val intent = Intent(this, CarDetailsActivity::class.java)
            intent.putExtra("selectedCar", car)
            startActivity(intent)
        }
        binding.carRecyclerView.adapter = carAdapter
    }

    private fun loadCars() {
        val carList = listOf(
            Car(
                "Model S",
                "Tesla",
                2022,
                "The Tesla Model S is an all-electric luxury sedan.",
                "https://ev-database.org/img/auto/Tesla_Model_S_2021/Tesla_Model_S_2021-01@2x.jpg",
                "https://www.tesla.com"
            ),
            Car(
                "Camry",
                "Toyota",
                2021,
                "The Toyota Camry is a reliable and efficient midsize sedan.",
                "https://scene7.toyota.eu/is/image/toyotaeurope/CAM0001a_21-2:Large-Landscape?ts=1671185294720&resMode=sharp2&op_usm=1.75,0.3,2,0",
                "https://www.toyota.com"
            ),
            Car(
                "Accord",
                "Honda",
                2023,
                "The Honda Accord is a popular and well-equipped midsize sedan.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Honda_Accord_%28CV3%29_EX_eHEV%2C_2021%2C_front.jpg/1200px-Honda_Accord_%28CV3%29_EX_eHEV%2C_2021%2C_front.jpg",
                "https://www.honda.com"
            ),
            Car(
                "Civic",
                "Honda",
                2022,
                "The Honda Civic is a compact car known for its reliability and fuel efficiency.",
                "https://cars.usnews.com/pics/size/640x420/images/Auto/custom/15550/2024_Honda_Civic_Angular_Front_1.jpg",
                "https://www.honda.com"
            ),
            Car(
                "Corolla",
                "Toyota",
                2021,
                "The Toyota Corolla is a compact sedan with a reputation for reliability.",
                "https://scene7.toyota.eu/is/image/toyotaeurope/toyota-corolla-hatchback-2019-gallery-06-full_tcm-10-1553823-6:Medium-Landscape?ts=0&resMode=sharp2&op_usm=1.75,0.3,2,0",
                "https://www.toyota.com"
            ),
            Car(
                "Mustang",
                "Ford",
                2023,
                "The Ford Mustang is an iconic American muscle car.",
                "https://www.ford.com/is/image/content/dam/vdm_ford/live/en_us/ford/nameplate/mustang/2024/collections/dm/24_FRD_MST_S5A0331_1e_V2.tif?croppathe=1_4x3&wid=900",
                "https://www.ford.com"
            ),
            Car(
                "Wrangler",
                "Jeep",
                2022,
                "The Jeep Wrangler is a rugged and capable off-road SUV.",
                "https://media.ed.edmunds-media.com/jeep/wrangler/2024/oem/2024_jeep_wrangler_convertible-suv_rubicon-392_fq_oem_1_1600.jpg",
                "https://www.jeep.com"
            ),
            Car(
                "X5",
                "BMW",
                2021,
                "The BMW X5 is a luxury SUV with advanced features and performance.",
                "https://www.bmw.hr/content/dam/bmw/common/all-models/x-series/x5/2023/highlights/bmw-x-series-x5-sp-desktop.jpg",
                "https://www.bmw.com"
            ),
            Car(
                "A4",
                "Audi",
                2023,
                "The Audi A4 is a premium compact sedan with refined styling and technology.",
                "https://www.bmw.hr/content/dam/bmw/common/all-models/x-series/x5/2023/highlights/bmw-x-series-x5-sp-desktop.jpg",
                "https://www.audi.com"
            ),
            Car(
                "Altima",
                "Nissan",
                2022,
                "The Nissan Altima is a midsize sedan with a spacious interior and modern features.",
                "https://hips.hearstapps.com/hmg-prod/images/2024-nissan-altima-sl-awd-163-6580987a4ce0f.jpg?crop=0.662xw:0.743xh;0.276xw,0.257xh&resize=768:*",
                "https://www.nissan.com"
            )
        )
        carAdapter.updateCars(carList)
    }

    private fun switchTheme() {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_theme -> {
                switchTheme()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}