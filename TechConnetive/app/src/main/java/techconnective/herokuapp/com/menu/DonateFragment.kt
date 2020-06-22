package techconnective.herokuapp.com.menu

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.ListOng
import model.OngAdapters
import techconnective.herokuapp.com.R
import techconnective.herokuapp.com.doacao.FormDonateActivity


class DonateFragment : Fragment(), OngAdapters.OnClickOng {

    private var recyclerView: RecyclerView? = null
    private var arrayList: ArrayList<ListOng>? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var brinquedo: View? = null
    private var roupa: View? = null
    private var food: View? = null
    private var tipo: TextView? = null

    private var valueType = "Roupa";


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donate, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

        brinquedo = view.findViewById(R.id.brinquedo)
        roupa = view.findViewById(R.id.roupa)
        food = view.findViewById(R.id.food)
        tipo = view.findViewById(R.id.tipo)
        tipo!!.text = "Roupa"


        brinquedo!!.setOnClickListener {
            brinquedo!!.setBackgroundResource(R.drawable.ic_checked)
            roupa!!.setBackgroundResource(R.drawable.ic_unchecked)
            food!!.setBackgroundResource(R.drawable.ic_unchecked)

            itemClicked(brinquedo!!, roupa!!, food!!, "Brinquedo")
        }
        roupa!!.setOnClickListener {
            brinquedo!!.setBackgroundResource(R.drawable.ic_unchecked)
            roupa!!.setBackgroundResource(R.drawable.ic_checked)
            food!!.setBackgroundResource(R.drawable.ic_unchecked)

            itemClicked(roupa!!, food!!, brinquedo!!, "Roupa")
        }
        food!!.setOnClickListener {
            brinquedo!!.setBackgroundResource(R.drawable.ic_unchecked)
            roupa!!.setBackgroundResource(R.drawable.ic_unchecked)
            food!!.setBackgroundResource(R.drawable.ic_checked)

            itemClicked(food!!, brinquedo!!, roupa!!, "Alimento")
        }

        return view
    }

    fun itemClicked(item1: View, item2: View, item3: View, valueType: String) {

        item1.getLayoutParams().height = 318;
        item1.getLayoutParams().width = 318;
        item1.requestLayout();

        item2.getLayoutParams().height = 258;
        item2.getLayoutParams().width = 258;
        item2.requestLayout();

        item3.getLayoutParams().height = 258;
        item3.getLayoutParams().width = 258;
        item3.requestLayout();

        tipo!!.text = valueType

        this.valueType = valueType

        val animatorX1 = ObjectAnimator.ofFloat(item1, "x", 385f)
        val animatorY1 = ObjectAnimator.ofFloat(item1, "y", 500f)
        //animatorX.duration = 1000
        val animatorBrinquedo = AnimatorSet()
        animatorBrinquedo.playTogether(animatorX1, animatorY1)
        animatorBrinquedo.start()

        val animatorX2 = ObjectAnimator.ofFloat(item2, "x", 820f)
        val animatorY2 = ObjectAnimator.ofFloat(item2, "y", 400f)
        //animatorX.duration = 1000
        val animatorRoupa = AnimatorSet()
        animatorRoupa.playTogether(animatorX2, animatorY2)
        animatorRoupa.start()

        val animatorX3 = ObjectAnimator.ofFloat(item3, "x", 0f)
        val animatorY3 = ObjectAnimator.ofFloat(item3, "y", 400f)
        //animatorX.duration = 1000
        val animatorFood = AnimatorSet()
        animatorFood.playTogether(animatorX3, animatorY3)
        animatorFood.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayoutManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        setDataInList()
        recyclerView?.adapter = OngAdapters(arrayList!!, this)
    }

    override fun onItemClick(item: ListOng, position: Int) {

        val intent = Intent(context, FormDonateActivity::class.java)

        intent.putExtra("icon", item.iconOng)
        intent.putExtra("nameOng", item.listOng)
        intent.putExtra("type", valueType)
        startActivity(intent)

    }

    private fun setDataInList() {

        arrayList!!.add(ListOng(R.drawable.latter_a, "Todos Juntos", null, null))
        arrayList!!.add(ListOng(R.drawable.latter_b, "AMAI", null, null))
        arrayList!!.add(ListOng(R.drawable.latter_c, "SÓ BEBÊ", null, null))
        arrayList!!.add(ListOng(R.drawable.latter_d, "YASMIN", null, null))
        arrayList!!.add(ListOng(R.drawable.latter_e, "despertar", null, null))
        arrayList!!.add(ListOng(R.drawable.latter_f, "Ágatha", null, null))
        arrayList!!.add(ListOng(R.drawable.latter_g, "Erguendo VIDAS", null, null))

    }
}
