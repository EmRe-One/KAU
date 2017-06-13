package ca.allanwang.kau.sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ca.allanwang.kau.kpref.KPrefActivity
import ca.allanwang.kau.kpref.KPrefAdapterBuilder
import ca.allanwang.kau.utils.darken
import ca.allanwang.kau.utils.navigationBarColor
import ca.allanwang.kau.utils.startActivitySlideIn
import ca.allanwang.kau.views.RippleCanvas
import com.mikepenz.google_material_typeface_library.GoogleMaterial


class MainActivity : KPrefActivity() {

    override fun onCreateKPrefs(savedInstanceState: android.os.Bundle?): KPrefAdapterBuilder.() -> Unit = {
        textColor = { KPrefSample.textColor }
        accentColor = { KPrefSample.accentColor }
        header(R.string.header)
        checkbox(title = R.string.checkbox_1, description = R.string.desc,
                getter = { KPrefSample.check1 }, setter = { KPrefSample.check1 = it })
        checkbox(title = R.string.checkbox_2,
                getter = { KPrefSample.check2 }, setter = { KPrefSample.check2 = it; reload(3) })
        checkbox(title = R.string.checkbox_3, description = R.string.desc_dependent, enabler = { KPrefSample.check2 },
                getter = { KPrefSample.check3 }, setter = { KPrefSample.check3 = it })
        colorPicker(title = R.string.text_color, description = R.string.color_custom,
                getter = { KPrefSample.textColor }, setter = { KPrefSample.textColor = it; reload() },
                configs = {
                    allowCustom = true
                })
        colorPicker(title = R.string.accent_color, description = R.string.color_no_custom,
                getter = { KPrefSample.accentColor }, setter = {
            KPrefSample.accentColor = it
            reload()
            val darkerColor = it.darken()
            this@MainActivity.navigationBarColor = darkerColor
            toolbarCanvas.ripple(darkerColor, RippleCanvas.MIDDLE, RippleCanvas.END, duration = 500L)
        },
                configs = {
                    allowCustom = false
                })
        colorPicker(iicon = GoogleMaterial.Icon.gmd_colorize,
                title = R.string.background_color, description = R.string.color_custom_alpha,
                getter = { KPrefSample.bgColor }, setter = { KPrefSample.bgColor = it; bgCanvas.ripple(it, duration = 500L) },
                configs = {
                    allowCustomAlpha = true
                    allowCustom = true
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bgCanvas.set(KPrefSample.bgColor)
        val darkAccent = KPrefSample.accentColor.darken()
        toolbarCanvas.set(darkAccent)
        this.navigationBarColor = darkAccent
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                startActivitySlideIn(AnimActivity::class.java, clearStack = true)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

}
