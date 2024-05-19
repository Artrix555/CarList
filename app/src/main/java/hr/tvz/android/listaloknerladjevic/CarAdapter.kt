package hr.tvz.android.listaloknerladjevic

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.listaloknerladjevic.databinding.ItemCarBinding

class CarAdapter(
    private var carList: List<Car>,
    private val onItemClick: (Car) -> Unit
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.bind(car)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCars(newCarList: List<Car>) {
        carList = newCarList
        notifyDataSetChanged()
    }

    inner class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val car = carList[position]
                    onItemClick(car)
                }
            }
        }

        fun bind(car: Car) {
            binding.modelTextView.text = car.model
            binding.manufacturerTextView.text = car.manufacturer
        }
    }
}