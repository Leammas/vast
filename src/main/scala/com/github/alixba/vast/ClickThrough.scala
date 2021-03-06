package com.github.alixba.vast

import scala.xml.Node

case class ClickThrough(value: String, id: Option[String]) extends VASTElement {

  /**
   * Serializes this to a Node.
   */
  def toXML: Node =
    <ClickThrough id={ id }>{ value.asCData }</ClickThrough>

}

object ClickThrough extends VASTElementCompanion[ClickThrough] {

  def apply(value: String): ClickThrough =
    ClickThrough(value, None)

  /**
   * Deserializes a Node to a T.
   * The highest tag of the Node should match
   * the T.
   *
   * {{{
   *   val elem = <Ad><SomeTags/></Ad>
   *   val ad = Ad.fromXML(elem)
   * }}}
   */
  def fromXML(node: Node): ClickThrough = {
    val value = node.text
    val id = (node \ "@id").headOption

    ClickThrough(value, id)
  }

}