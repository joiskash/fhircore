package org.smartregister.fhircore.quest.custom

import ca.uhn.fhir.model.api.annotation.Child
import ca.uhn.fhir.model.api.annotation.ResourceDef
import org.hl7.fhir.r4.model.Base
import org.hl7.fhir.r4.model.Property
import org.hl7.fhir.r4.model.StringType

@ResourceDef(
    name = "IMMZ-C-register-client",
    profile =
        "http://fhir.org/guides/who/smart-immunization/StructureDefinition/IMMZ-C-register-client")
class IMMZ_LM : Base() {
  @Child(name = "uniqueId", min = 0, max = 1, order = 1, type = arrayOf(StringType::class))
  var uniqueId: StringType = StringType("")

  override fun fhirType(): String {
    return "IMMZ-C-register-client"
  }

  override fun listChildren(p0: MutableList<Property>?) {
    p0?.add(Property("uniqueId", "StringType", "", 0, 1, this.uniqueId))
  }

  override fun getIdBase(): String {
    return "base"
  }

  override fun setIdBase(p0: String?) {
    val s = p0
  }

  override fun setProperty(name: String?, value: Base?): Base {
    when (name) {
      "uniqueId" -> this.uniqueId = value as StringType
    }
    return super.setProperty(name, value)
  }

  override fun getProperty(hash: Int, name: String?, checkValid: Boolean): Array<Base> {
    return when (hash) {
      -294460212 -> {
        arrayOf(this.uniqueId as Base)
      }
      else -> super.getProperty(hash, name, checkValid)
    }
  }

  override fun setProperty(hash: Int, name: String?, value: Base?): Base {
    return when (hash) {
      -294460212 -> {
        this.uniqueId = value as StringType
        this.uniqueId
      }
      else -> super.setProperty(hash, name, value)
    }
  }
}
