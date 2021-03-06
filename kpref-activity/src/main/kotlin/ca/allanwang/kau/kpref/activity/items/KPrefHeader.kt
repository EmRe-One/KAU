package ca.allanwang.kau.kpref.activity.items

import ca.allanwang.kau.kpref.activity.R

/**
 * Created by Allan Wang on 2017-06-07.
 *
 * Header preference
 * This view just holds a title and is not clickable. It is styled using the accent color
 */
open class KPrefHeader(builder: CoreContract) : KPrefItemCore(builder) {

    override fun getLayoutRes(): Int = R.layout.kau_pref_header

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)
        withAccentColor(holder.title::setTextColor)
    }

    override fun getType() = R.id.kau_item_pref_header

}