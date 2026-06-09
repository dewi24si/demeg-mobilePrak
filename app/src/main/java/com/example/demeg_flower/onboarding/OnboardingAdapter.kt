package com.example.demeg_flower.onboarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(activity: OnboardingActivity) : FragmentStateAdapter(activity) {

    private val fragments: List<Fragment> = listOf(
        Onboarding1Fragment(),
        Onboarding2Fragment(),
        Onboarding3Fragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
