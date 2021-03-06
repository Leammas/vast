package com.github.alixba.vast

import scala.xml.Node

case class InLineCompanion(element: Resource, creativeExtensions: Option[Seq[CreativeExtension]],
                           trackingEvents: Option[Seq[Tracking]], companionClickThrough: Option[CompanionClickThrough],
                           altText: Option[AltText], adParameters: Option[AdParameters], id: Option[String], width: Int,
                           height: Int, assetWidth: Option[Int], assetHeight: Option[Int], expandedWidth: Option[Int],
                           expandedHeight: Option[Int], apiFramework: Option[String], adSlotId: Option[String])
    extends Companion {

  /**
   * Serializes this to a Node.
   */
  def toXML: Node = {
    val elementXML = element.toXML
    val creativeExtensionsXML = creativeExtensions
      .map(n ⇒ <CreativeExtensions>{ n.map(_.toXML) }</CreativeExtensions>).toSeq
    val trackingEventsXML = trackingEvents.map(n ⇒ <TrackingEvents>{ n.map(_.toXML) }</TrackingEvents>).toSeq
    val companionClickThroughXML = companionClickThrough.map(_.toXML).toSeq
    val altTextXML = altText.map(_.toXML).toSeq
    val adParametersXML = adParameters.map(_.toXML).toSeq

    <Companion id={ id } width={ width } height={ height } assetWidth={ assetWidth } assetHeight={ assetHeight } expandedWidth={ expandedWidth } expandedHeight={ expandedHeight } apiFramework={ apiFramework } adSlotId={ adSlotId }>{ elementXML }{ creativeExtensionsXML }{ trackingEventsXML }{ companionClickThroughXML }{ altTextXML }{ adParametersXML }</Companion>
  }

}

object InLineCompanion extends VASTElementCompanion[InLineCompanion] {

  def apply(width: Int, height: Int, element: Resource): InLineCompanion =
    InLineCompanion(element, None, None, None, None, None, None, width, height, None, None, None, None, None, None)

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
  def fromXML(node: Node): InLineCompanion = {
    val element = Resource.fromXML(node)
    val creativeExtensions = (node \ "CreativeExtensions")
      .headOption.map(n ⇒ (n \ "CreativeExtension").map(CreativeExtension.fromXML))
    val trackingEvents = (node \ "TrackingEvents").headOption.map(n ⇒ (n \ "Tracking").map(Tracking.fromXML))
    val companionClickThrough = (node \ "CompanionClickThrough").headOption.map(CompanionClickThrough.fromXML)
    val altText = (node \ "AltText").headOption.map(AltText.fromXML)
    val adParameters = (node \ "AdParameters").headOption.map(AdParameters.fromXML)
    val id = (node \ "@id").headOption
    val width = (node \ "@width").headOption.getOrElseMissingException("width")
    val height = (node \ "@height").headOption.getOrElseMissingException("height")
    val assetWidth = (node \ "@assetWidth").headOption
    val assetHeight = (node \ "@assetHeight").headOption
    val expandedWidth = (node \ "@expandedWidth").headOption
    val expandedHeight = (node \ "@expandedHeight").headOption
    val apiFramework = (node \ "@apiFramework").headOption
    val adSlotId = (node \ "@adSlotId").headOption

    InLineCompanion(element, creativeExtensions, trackingEvents, companionClickThrough, altText, adParameters, id,
      width, height, assetWidth, assetHeight, expandedWidth, expandedHeight, apiFramework, adSlotId)
  }

}