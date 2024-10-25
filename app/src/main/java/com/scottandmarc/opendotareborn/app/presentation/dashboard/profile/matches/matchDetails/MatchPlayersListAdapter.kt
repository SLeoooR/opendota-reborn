package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.matches.matchDetails

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.MatchPlayer
import com.scottandmarc.opendotareborn.databinding.PlayerListItemBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.roundToInt

class MatchPlayersListAdapter(
    private val matchPlayers: List<MatchPlayer>,
) : RecyclerView.Adapter<MatchPlayersListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: PlayerListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (binding.llPlayerDetails.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(
                    binding.llPlayer,
                    AutoTransition()
                )
                binding.llPlayerDetails.visibility = View.VISIBLE
            } else {
                binding.llPlayerDetails.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val playerListItemBinding = PlayerListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(playerListItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val heroInfoRepository = DependencyInjector.provideHeroInfoRepository(viewHolder.binding.root.context)
        if (this.matchPlayers.isNotEmpty()) {
            val player: MatchPlayer = matchPlayers[position]

            if (position % 2 == 0) {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_background_color))
                viewHolder.binding.llSecondHeader.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_card_color))
            } else {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_card_color))
                viewHolder.binding.llSecondHeader.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_background_color))
            }

            viewHolder.binding.tvPlayerName.text = if (player.personaname.toString() == "null") {
                "<hidden>"
            } else {
                player.personaname.toString()
            }

            val kdaString = "${player.kills}/${player.deaths}/${player.assists}"
            viewHolder.binding.tvKDA.text = kdaString

            viewHolder.binding.tvLvl.text = player.level.toString()
            viewHolder.binding.tvNet.text = if (player.netWorth!! < 1000) {
                player.netWorth.toString()
            } else {
                format(player.netWorth)
            }

            Log.d("matchPlayer", player.toString())

            val circularProgressDrawable = CircularProgressDrawable(viewHolder.itemView.context).apply {
                strokeWidth = 3F
                centerRadius = 10F
                setColorSchemeColors(ContextCompat.getColor(viewHolder.itemView.context, R.color.white))
                start()
            }

            if (player.heroId != 0) {
                val heroInfo = player.heroId?.let { heroInfoRepository.getHeroInfoWhere(it) }
                val heroIconPicURL = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/heroes/${heroInfo?.name?.substring(14)}.png"
                Picasso.get().load(heroIconPicURL).error(R.drawable.ic_question_mark).placeholder(circularProgressDrawable).into(viewHolder.binding.ivHeroIcon)
            } else {
                viewHolder.binding.ivHeroIcon.setImageResource(R.drawable.ic_question_mark)
            }

            // More Details
            val lhDn = "${player.lastHits}/${player.denies}"
            viewHolder.binding.tvLhDn.text = lhDn
            val heroDamage = format(player.heroDamage?: 0)
            viewHolder.binding.tvHeroDamage.text = heroDamage
            val towerDamage = format(player.towerDamage?: 0)
            viewHolder.binding.tvTowerDamage.text = towerDamage
            val heroHealing = format(player.heroHealing?: 0)
            viewHolder.binding.tvHeroHealing.text = heroHealing
            val gpmXpm = "${player.goldPerMin}/${player.xpPerMin}"
            viewHolder.binding.tvGpmXpm.text = gpmXpm

//            // More Details
//            val lhDn = "${viewHolder.binding.tvLhDn.text}${player.lastHits}/${player.denies}"
//            viewHolder.binding.tvLhDn.text = lhDn
//            val heroDamage = "${viewHolder.binding.tvHeroDamage.text}${NumberFormat.getNumberInstance(Locale.US).format(player.heroDamage)}"
//            viewHolder.binding.tvHeroDamage.text = heroDamage
//            val towerDamage = "${viewHolder.binding.tvTowerDamage.text}${NumberFormat.getNumberInstance(Locale.US).format(player.towerDamage)}"
//            viewHolder.binding.tvTowerDamage.text = towerDamage
//            val heroHealing = "${viewHolder.binding.tvHeroHealing.text}${NumberFormat.getNumberInstance(Locale.US).format(player.heroHealing)}"
//            viewHolder.binding.tvHeroHealing.text = heroHealing
//            val gpmXpm = "${viewHolder.binding.tvGpmXpm.text}${player.goldPerMin}/${player.xpPerMin}"
//            viewHolder.binding.tvGpmXpm.text = gpmXpm

            if (player.item0 != 0) {
                val item0Url = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/items/${items[player.item0]}.png"
                Picasso.get().load(item0Url).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivItem0)
            }
            if (player.item1 != 0) {
                val item1Url = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/items/${items[player.item1]}.png"
                Picasso.get().load(item1Url).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivItem1)
            }
            if (player.item2 != 0) {
                val item2Url = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/items/${items[player.item2]}.png"
                Picasso.get().load(item2Url).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivItem2)
            }
            if (player.item3 != 0) {
                val item3Url = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/items/${items[player.item3]}.png"
                Picasso.get().load(item3Url).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivItem3)
            }
            if (player.item4 != 0) {
                val item4Url = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/items/${items[player.item4]}.png"
                Picasso.get().load(item4Url).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivItem4)
            }
            if (player.item5 != 0) {
                val item5Url = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/items/${items[player.item5]}.png"
                Picasso.get().load(item5Url).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivItem5)
            }
            if (player.itemNeutral != 0) {
                val itemNeutral = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/items/${items[player.itemNeutral]}.png"
                Picasso.get().load(itemNeutral).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivItemNeutral)
            }

            if (player.backpack0 != 0) {
                val backpack0 = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/items/${items[player.backpack0]}.png"
                Picasso.get().load(backpack0).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivBackpack0)
            }
            if (player.backpack1 != 0) {
                val backpack1 = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/items/${items[player.backpack1]}.png"
                Picasso.get().load(backpack1).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivBackpack1)
            }
            if (player.backpack2 != 0) {
                val backpack2 = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/items/${items[player.backpack2]}.png"
                Picasso.get().load(backpack2).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivBackpack2)
            }
        }
    }


    override fun getItemCount(): Int = matchPlayers.size
}

fun format(num: Int): String {
    val suffix: Array<String> = arrayOf("k","m","b","t")
    var size = if (num !=0) {
        log10(num.toDouble()).toInt()
    } else {
        0
    }

    if (size >= 3) {
        while (size % 3 != 0) {
            size -= 1
        }
    }

    val notation = 10f.pow(size)
    val result = if (size >= 3) {
        "${(((num / notation) * 100).roundToInt() / 100.0f)}${suffix[(size / 3) - 1]}"
    } else {
        num.toString()
    }
    return result
}

val items: Map<Int, String> = mapOf(
    0 to "ability_base",
    1 to "blink",
    2 to "blades_of_attack",
    3 to "broadsword",
    4 to "chainmail",
    5 to "claymore",
    6 to "helm_of_iron_will",
    7 to "javelin",
    8 to "mithril_hammer",
    9 to "platemail",
    10 to "quarterstaff",
    11 to "quelling_blade",
    12 to "ring_of_protection",
    13 to "gauntlets",
    14 to "slippers",
    15 to "mantle",
    16 to "branches",
    17 to "belt_of_strength",
    18 to "boots_of_elves",
    19 to "robe",
    20 to "circlet",
    21 to "ogre_axe",
    22 to "blade_of_alacrity",
    23 to "staff_of_wizardry",
    24 to "ultimate_orb",
    25 to "gloves",
    26 to "lifesteal",
    27 to "ring_of_regen",
    28 to "sobi_mask",
    29 to "boots",
    30 to "gem",
    31 to "cloak",
    32 to "talisman_of_evasion",
    33 to "cheese",
    34 to "magic_stick",
    35 to "recipe_magic_wand",
    36 to "magic_wand",
    37 to "ghost",
    38 to "clarity",
    39 to "flask",
    40 to "dust",
    41 to "bottle",
    42 to "ward_observer",
    43 to "ward_sentry",
    44 to "tango",
    45 to "courier",
    46 to "tpscroll",
    47 to "recipe_travel_boots",
    48 to "travel_boots",
    49 to "recipe_phase_boots",
    50 to "phase_boots",
    51 to "demon_edge",
    52 to "eagle",
    53 to "reaver",
    54 to "relic",
    55 to "hyperstone",
    56 to "ring_of_health",
    57 to "void_stone",
    58 to "mystic_staff",
    59 to "energy_booster",
    60 to "point_booster",
    61 to "vitality_booster",
    62 to "recipe_power_treads",
    63 to "power_treads",
    64 to "recipe_hand_of_midas",
    65 to "hand_of_midas",
    66 to "recipe_oblivion_staff",
    67 to "oblivion_staff",
    68 to "recipe_pers",
    69 to "pers",
    70 to "recipe_poor_mans_shield",
    71 to "poor_mans_shield",
    72 to "recipe_bracer",
    73 to "bracer",
    74 to "recipe_wraith_band",
    75 to "wraith_band",
    76 to "recipe_null_talisman",
    77 to "null_talisman",
    78 to "recipe_mekansm",
    79 to "mekansm",
    80 to "recipe_vladmir",
    81 to "vladmir",
    85 to "recipe_buckler",
    86 to "buckler",
    87 to "recipe_ring_of_basilius",
    88 to "ring_of_basilius",
    89 to "recipe_pipe",
    90 to "pipe",
    91 to "recipe_urn_of_shadows",
    92 to "urn_of_shadows",
    93 to "recipe_headdress",
    94 to "headdress",
    95 to "recipe_sheepstick",
    96 to "sheepstick",
    97 to "recipe_orchid",
    98 to "orchid",
    99 to "recipe_cyclone",
    100 to "cyclone",
    101 to "recipe_force_staff",
    102 to "force_staff",
    103 to "recipe_dagon",
    104 to "dagon",
    105 to "recipe_necronomicon",
    106 to "necronomicon",
    107 to "recipe_ultimate_scepter",
    108 to "ultimate_scepter",
    109 to "recipe_refresher",
    110 to "refresher",
    111 to "recipe_assault",
    112 to "assault",
    113 to "recipe_heart",
    114 to "heart",
    115 to "recipe_black_king_bar",
    116 to "black_king_bar",
    117 to "aegis",
    118 to "recipe_shivas_guard",
    119 to "shivas_guard",
    120 to "recipe_bloodstone",
    121 to "bloodstone",
    122 to "recipe_sphere",
    123 to "sphere",
    124 to "recipe_vanguard",
    125 to "vanguard",
    126 to "recipe_blade_mail",
    127 to "blade_mail",
    128 to "recipe_soul_booster",
    129 to "soul_booster",
    130 to "recipe_hood_of_defiance",
    131 to "hood_of_defiance",
    132 to "recipe_rapier",
    133 to "rapier",
    134 to "recipe_monkey_king_bar",
    135 to "monkey_king_bar",
    136 to "recipe_radiance",
    137 to "radiance",
    138 to "recipe_butterfly",
    139 to "butterfly",
    140 to "recipe_greater_crit",
    141 to "greater_crit",
    142 to "recipe_basher",
    143 to "basher",
    144 to "recipe_bfury",
    145 to "bfury",
    146 to "recipe_manta",
    147 to "manta",
    148 to "recipe_lesser_crit",
    149 to "lesser_crit",
    150 to "recipe_armlet",
    151 to "armlet",
    152 to "invis_sword",
    153 to "recipe_sange_and_yasha",
    154 to "sange_and_yasha",
    155 to "recipe_satanic",
    156 to "satanic",
    157 to "recipe_mjollnir",
    158 to "mjollnir",
    159 to "recipe_skadi",
    160 to "skadi",
    161 to "recipe_sange",
    162 to "sange",
    163 to "recipe_helm_of_the_dominator",
    164 to "helm_of_the_dominator",
    165 to "recipe_maelstrom",
    166 to "maelstrom",
    167 to "recipe_desolator",
    168 to "desolator",
    169 to "recipe_yasha",
    170 to "yasha",
    171 to "recipe_mask_of_madness",
    172 to "mask_of_madness",
    173 to "recipe_diffusal_blade",
    174 to "diffusal_blade",
    175 to "recipe_ethereal_blade",
    176 to "ethereal_blade",
    177 to "recipe_soul_ring",
    178 to "soul_ring",
    179 to "recipe_arcane_boots",
    180 to "arcane_boots",
    181 to "orb_of_venom",
    182 to "stout_shield",
    183 to "recipe_invis_sword",
    184 to "recipe_ancient_janggo",
    185 to "ancient_janggo",
    186 to "recipe_medallion_of_courage",
    187 to "medallion_of_courage",
    188 to "smoke_of_deceit",
    189 to "recipe_veil_of_discord",
    190 to "veil_of_discord",
    191 to "recipe_necronomicon_2",
    192 to "recipe_necronomicon_3",
    193 to "necronomicon_2",
    194 to "necronomicon_3",
    196 to "diffusal_blade_2",
    197 to "recipe_dagon_2",
    198 to "recipe_dagon_3",
    199 to "recipe_dagon_4",
    200 to "recipe_dagon_5",
    201 to "dagon_2",
    202 to "dagon_3",
    203 to "dagon_4",
    204 to "dagon_5",
    205 to "recipe_rod_of_atos",
    206 to "rod_of_atos",
    207 to "recipe_abyssal_blade",
    208 to "abyssal_blade",
    209 to "recipe_heavens_halberd",
    210 to "heavens_halberd",
    211 to "recipe_ring_of_aquila",
    212 to "ring_of_aquila",
    213 to "recipe_tranquil_boots",
    214 to "tranquil_boots",
    215 to "shadow_amulet",
    216 to "enchanted_mango",
    217 to "recipe_ward_dispenser",
    218 to "ward_dispenser",
    219 to "recipe_travel_boots_2",
    220 to "travel_boots_2",
    221 to "recipe_lotus_orb",
    222 to "recipe_meteor_hammer",
    223 to "meteor_hammer",
    224 to "recipe_nullifier",
    225 to "nullifier",
    226 to "lotus_orb",
    227 to "recipe_solar_crest",
    228 to "recipe_octarine_core",
    229 to "solar_crest",
    230 to "recipe_guardian_greaves",
    231 to "guardian_greaves",
    232 to "aether_lens",
    233 to "recipe_aether_lens",
    234 to "recipe_dragon_lance",
    235 to "octarine_core",
    236 to "dragon_lance",
    237 to "faerie_fire",
    238 to "recipe_iron_talon",
    239 to "iron_talon",
    240 to "blight_stone",
    241 to "tango_single",
    242 to "crimson_guard",
    243 to "recipe_crimson_guard",
    244 to "wind_lace",
    245 to "recipe_bloodthorn",
    246 to "recipe_moon_shard",
    247 to "moon_shard",
    248 to "recipe_silver_edge",
    249 to "silver_edge",
    250 to "bloodthorn",
    251 to "recipe_echo_sabre",
    252 to "echo_sabre",
    253 to "recipe_glimmer_cape",
    254 to "glimmer_cape",
    255 to "recipe_aeon_disk",
    256 to "aeon_disk",
    257 to "tome_of_knowledge",
    258 to "recipe_kaya",
    259 to "kaya",
    260 to "refresher_shard",
    261 to "crown",
    262 to "recipe_hurricane_pike",
    263 to "hurricane_pike",
    265 to "infused_raindrop",
    266 to "recipe_spirit_vessel",
    267 to "spirit_vessel",
    268 to "recipe_holy_locket",
    269 to "holy_locket",
    270 to "recipe_ultimate_scepter_2",
    271 to "ultimate_scepter_2",
    272 to "recipe_kaya_and_sange",
    273 to "kaya_and_sange",
    274 to "recipe_yasha_and_kaya",
    275 to "recipe_trident",
    276 to "combo_breaker",
    277 to "yasha_and_kaya",
    279 to "ring_of_tarrasque",
    286 to "flying_courier",
    287 to "keen_optic",
    288 to "grove_bow",
    289 to "quickening_charm",
    290 to "philosophers_stone",
    291 to "force_boots",
    292 to "desolator_2",
    293 to "phoenix_ash",
    294 to "seer_stone",
    295 to "greater_mango",
    297 to "vampire_fangs",
    298 to "craggy_coat",
    299 to "greater_faerie_fire",
    300 to "timeless_relic",
    301 to "mirror_shield",
    302 to "elixer",
    303 to "recipe_ironwood_tree",
    304 to "ironwood_tree",
    305 to "royal_jelly",
    306 to "pupils_gift",
    307 to "tome_of_aghanim",
    308 to "repair_kit",
    309 to "mind_breaker",
    310 to "third_eye",
    311 to "spell_prism",
    312 to "horizon",
    313 to "fusion_rune",
    317 to "recipe_fallen_sky",
    325 to "princes_knife",
    326 to "spider_legs",
    327 to "helm_of_the_undying",
    328 to "mango_tree",
    330 to "witless_shako",
    331 to "vambrace",
    334 to "imp_claw",
    335 to "flicker",
    336 to "spy_gadget",
    349 to "arcane_ring",
    354 to "ocean_heart",
    355 to "broom_handle",
    356 to "trusty_shovel",
    357 to "nether_shawl",
    358 to "dragon_scale",
    359 to "essence_ring",
    360 to "clumsy_net",
    361 to "enchanted_quiver",
    362 to "ninja_gear",
    363 to "illusionsts_cape",
    364 to "havoc_hammer",
    365 to "panic_button",
    366 to "apex",
    367 to "ballista",
    368 to "woodland_striders",
    369 to "trident",
    370 to "demonicon",
    371 to "fallen_sky",
    372 to "pirate_hat",
    373 to "dimensional_doorway",
    374 to "ex_machina",
    375 to "faded_broach",
    376 to "paladin_sword",
    377 to "minotaur_horn",
    378 to "orb_of_destruction",
    379 to "the_leveller",
    381 to "titan_sliver",
    473 to "voodoo_mask",
    485 to "blitz_knuckles",
    533 to "recipe_witch_blade",
    534 to "witch_blade",
    565 to "chipped_vest",
    566 to "wizard_glass",
    569 to "orb_of_corrosion",
    570 to "gloves_of_travel",
    571 to "trickster_cloak",
    573 to "elven_tunic",
    574 to "cloak_of_flames",
    575 to "venom_gland",
    576 to "gladiator_helm",
    577 to "possessed_mask",
    578 to "ancient_perseverance",
    582 to "oakheart",
    585 to "stormcrafter",
    588 to "overflowing_elixir",
    589 to "mysterious_hat",
    593 to "fluffy_hat",
    596 to "falcon_blade",
    597 to "recipe_mage_slayer",
    598 to "mage_slayer",
    599 to "recipe_falcon_blade",
    600 to "overwhelming_blink",
    603 to "swift_blink",
    604 to "arcane_blink",
    606 to "recipe_arcane_blink",
    607 to "recipe_swift_blink",
    608 to "recipe_overwhelming_blink",
    609 to "aghanims_shard",
    610 to "wind_waker",
    612 to "recipe_wind_waker",
    633 to "recipe_helm_of_the_overlord",
    635 to "helm_of_the_overlord",
    637 to "star_mace",
    638 to "penta_edged_sword",
    640 to "recipe_orb_of_corrosion",
    653 to "recipe_grandmasters_glaive",
    655 to "grandmasters_glaive",
    674 to "warhammer",
    675 to "psychic_headband",
    676 to "ceremonial_robe",
    677 to "book_of_shadows",
    678 to "giants_ring",
    679 to "vengeances_shadow",
    680 to "bullwhip",
    686 to "quicksilver_amulet",
    691 to "recipe_eternal_shroud",
    692 to "eternal_shroud",
    725 to "aghanims_shard_roshan",
    727 to "ultimate_scepter_roshan",
    731 to "satchel",
    824 to "assassins_dagger",
    825 to "ascetic_cap",
    826 to "sample_picker",
    827 to "icarus_wings",
    828 to "misericorde",
    829 to "force_field",
    834 to "black_powder_bag",
    835 to "paintball",
    836 to "light_robes",
    837 to "heavy_blade",
    838 to "unstable_wand",
    839 to "fortitude_ring",
    840 to "pogo_stick",
    849 to "mechanical_arm",
    907 to "recipe_wraith_pact",
    908 to "wraith_pact",
    910 to "recipe_revenants_brooch",
    911 to "revenants_brooch",
    930 to "recipe_boots_of_bearing",
    931 to "boots_of_bearing",
    938 to "slime_vial",
    939 to "harpoon",
    940 to "wand_of_the_brine",
    945 to "seeds_of_serenity",
    946 to "lance_of_pursuit",
    947 to "occult_bracelet",
    948 to "tome_of_omniscience",
    949 to "ogre_seal_totem",
    950 to "defiant_shell",
    968 to "arcane_scout",
    969 to "barricade",
    990 to "eye_of_the_vizier",
    998 to "manacles_of_power",
    1000 to "bottomless_chalice",
    1017 to "wand_of_sanctitude",
    1021 to "river_painter",
    1022 to "river_painter2",
    1023 to "river_painter3",
    1024 to "river_painter4",
    1025 to "river_painter5",
    1026 to "river_painter6",
    1027 to "river_painter7",
    1028 to "mutation_tombstone",
    1029 to "super_blink",
    1030 to "pocket_tower",
    1032 to "pocket_roshan",
    1076 to "specialists_array",
    1077 to "dagger_of_ristul",
    1090 to "muertas_gun",
    1091 to "samurai_tabi",
    1092 to "recipe_hermes_sandals",
    1093 to "hermes_sandals",
    1094 to "recipe_lunar_crest",
    1095 to "lunar_crest",
    1096 to "recipe_disperser",
    1097 to "disperser",
    1098 to "recipe_samurai_tabi",
    1099 to "recipe_witches_switch",
    1100 to "witches_switch",
    1101 to "recipe_harpoon",
    1106 to "recipe_phylactery",
    1107 to "phylactery",
    1122 to "diadem",
    1123 to "blood_grenade",
    1124 to "spark_of_courage",
    1125 to "cornucopia",
    1127 to "recipe_pavise",
    1128 to "pavise",
    1154 to "royale_with_cheese",
    1156 to "ancient_guardian",
    1157 to "safety_bubble",
    1158 to "whisper_of_the_dread",
    1159 to "nemesis_curse",
    1160 to "avianas_feather",
    1161 to "unwavering_condition",
    1162 to "halo",
    1163 to "recipe_aetherial_halo",
    1164 to "aetherial_halo",
    1167 to "light_collector",
    1168 to "rattlecage",
    1440 to "black_grimoire",
    1441 to "grisgris",
    1466 to "gungir",
    1487 to "claddish_spyglass",
    1565 to "recipe_gungir",
    1800 to "recipe_caster_rapier",
    1801 to "caster_rapier",
    1802 to "tiara_of_selemene",
    1803 to "doubloon",
    1804 to "roshans_banner",
    1805 to "recipe_devastator",
    1806 to "devastator",
    1807 to "recipe_angels_demise",
    1808 to "angels_demise",
    2091 to "tier1_token",
    2092 to "tier2_token",
    2093 to "tier3_token",
    2094 to "tier4_token",
    2095 to "tier5_token",
    2096 to "vindicators_axe",
    2097 to "duelist_gloves",
    2098 to "horizons_equilibrium",
    2099 to "blighted_spirit",
    2190 to "dandelion_amulet",
    2191 to "turtle_shell",
    2192 to "martyrs_plate",
    2193 to "gossamer_cape",
    4204 to "famango",
    4205 to "great_famango",
    4206 to "greater_famango",
    4207 to "recipe_great_famango",
    4208 to "recipe_greater_famango",
    4300 to "ofrenda",
    4301 to "ofrenda_shovel",
    4302 to "ofrenda_pledge"
)