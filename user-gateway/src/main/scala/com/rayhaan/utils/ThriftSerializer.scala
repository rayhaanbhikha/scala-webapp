package com.rayhaan.utils

import org.json4s.FieldSerializer

object ThriftSerializer extends FieldSerializer[Any](FieldSerializer.ignore("_passthroughFields"))
