package com.christophprenissl.hygienecompanion.util

import androidx.compose.ui.unit.dp

//sizes
const val halfSize = 0.5f
val cardPadding = 4.dp
val standardPadding = 8.dp
val doubleStandardPadding = standardPadding.times(2)
val standardElevation = 4.dp

//views
const val HOME_ROUTE = "home_route"
const val LOGGED_OUT_ROUTE = "logged_out_route"
const val COVERING_LETTERS_ROUTE = "covering_letter_route"
const val LAB_ROUTE = "lab_route"
const val COVERING_LETTER_BASIS_ROUTE = "covering_letter_basis_route"
const val COVERING_BASIS_ROUTE = "basis_detail_route"
const val REPORTS_ROUTE = "reports_route"
const val PROFILE_ROUTE = "profile_route"

//swipe to delete
const val SWIPE_THRESHOLD = 0.8f

//string resources
const val ADDRESS_NAME = "Addressbezeichnung"
const val ADDRESS = "Adresse"
const val ZIP = "Postleitzahl"
const val STREET = "Straße"
const val CITY_NAME = "Stadtname"
const val CONTACT = "Kontakt: "
const val CONTACT_NAME = "Kontaktperson"
const val CONTACT_DATA = "Kontaktdaten"
const val PHONE = "Telefon"
const val FAX = "Fax"
const val EMAIL = "E-Mail"
const val NORM = "Norm/Grundlage"
const val BASIC_PARAMETERS = "Grundlegende Parameter"
const val COVERING_SAMPLE_PARAMETERS = "Probeentnahme-Parameter"
const val LAB_SAMPLE_PARAMETERS = "Grundlegende Parameter für Laborannahme"
const val LAB_REPORT_PARAMETERS = "Laborbefund Parameter"

const val COVERING_LETTER_BASIS_DATA = "Begleitschein-Basis-Daten"
const val COVERING_BASIS = "Begleitschein-Normen/Grundlagen"
const val COVERING_LETTER_SERIES = "Begleitschein-Serien"
const val ADD_COVERING_BASIS = "Norm/Grundlage hinzufügen"
const val ADD_ADDRESS = "Adresse hinzufügen"
const val SAVE_ADDRESS = "Adresse speichern"
const val ADD_SAMPLE_LOCATION = "Neue Probeentnahmestelle"
const val SAMPLE_LOCATION = "Probeentnahmestelle"
const val DESCRIPTION = "Beschreibung"
const val EXTRA_INFO = "Zusätzliche Information"
const val NEXT_HEATER = "Nächste Heizung"
const val SAVE_SAMPLE_LOCATION = "Speichern"
const val ADD_PARAMETER = "Parameter hinzufügen"

//communication UI
const val LOADING = "Lädt gerade"
const val ERROR = "Fehler"
const val SUCCESS = "Aktion war erfolgreich"
const val ACCEPT = "Ok"
const val CANCEL = "Abbrechen"
const val DELETE = "Löschen"

//firebase
const val SAMPLE_LOCATIONS_FIRESTORE = "sample_locations"
const val ADDRESSES_FIRESTORE = "addresses"
const val BASES_FIRESTORE = "bases"
const val USERS_FIRESTORE = "user"
const val COVERING_LETTER_SERIES_FIRESTORE = "covering_letter_series"
const val ADDRESS_ID = "addressId"
//collection path
const val COVERING_LETTER_COLLECTION = "coveringLetters"
const val SAMPLE_COLLECTION = "samples"
//query parameter
const val ENDED_DATE = "endedDate"
