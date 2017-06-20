package edu.db.driver.physical.serializers

import edu.db.driver.data.DBConfiguration
import edu.db.driver.physical.data.Relation
import scala.xml.Elem
import scala.collection.mutable.ListBuffer

object RelationSerializer {
  def serialize(relation: Relation)(implicit config: DBConfiguration): Unit = {
    def serializeRecord(record: Relation#Record): Elem =
      <Record>
        { for ((key, value) <- record) yield <column key={ key } value={ value }/> }
      </Record>
    def serializeHeader(header: Map[String, String]): Elem =
      <header>
        { for ((key, value) <- header) yield <column name={ key } type={ value }/> }
      </header>

    val xmlElem =
      <table name={ relation.name }>
        { serializeHeader(relation.header) }
        <Records>
          { for (record <- relation.records) yield serializeRecord(record) }
        </Records>
      </table>
    scala.xml.XML.save(config.path + s"\\${relation.name}.xml", xmlElem)
  }
}