package com.github.alixba.vast

import scala.xml.Node

case class VASTAdTagURI(value: String) extends VASTElement {

  /**
   * Serializes this to a Node.
   */
  def toXML: Node =
    <VASTAdTagURI>{ value.asCData }</VASTAdTagURI>

}

object VASTAdTagURI extends VASTElementCompanion[VASTAdTagURI] {

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
  def fromXML(node: Node): VASTAdTagURI =
    VASTAdTagURI(node.text)

}