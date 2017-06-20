package edu.db.driver.physical.parsers

import scala.xml.Elem
import scala.xml.NodeSeq

import edu.db.driver.data.DBConfiguration
import edu.db.driver.physical.data.Relation

object RelationParser {
  def parse(name: String)(implicit config: DBConfiguration): Relation = {
    val xmlElem: Elem = scala.xml.XML.loadFile(config.path + s"\\${name}.xml")
    val header = (for {
      header <- (xmlElem \ "header")
      col <- (header \ "column")
      k <- col.attribute("name")
      v <- col.attribute("type")
    } yield (k.text, v.text)).toMap

    def toRecord(n: NodeSeq) = (for {
      col <- (n \ "column")
      k <- col.attribute("key")
      v <- col.attribute("value")
    } yield (k.text -> v.text)).toMap
    
    val records = for {
      records <- (xmlElem \ "Records")
      record <- (records \ "Record")
    } yield (toRecord(records \ "Record"))
    
    val relation = new Relation(name, header)
    relation.records ++= records
    relation
  }
}