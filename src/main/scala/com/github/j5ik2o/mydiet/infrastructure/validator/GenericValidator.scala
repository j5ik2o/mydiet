/*
 * Copyright 2014 Sisioh Project and others. (http://www.sisioh.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.github.j5ik2o.mydiet.infrastructure.validator

import scalaz._
import Scalaz._
import scala.util.Try
import java.net.URL
import org.sisioh.dddbase.core.model.Identifier
import com.eaio.uuid.UUID

/**
 * 汎用バリデーション。
 */
object GenericValidator {

  /**
   * 入力された文字列を必須の値として検証する。
   *
   * @param name パラメータ名
   * @param value 入力値
   * @return [[scalaz.ValidationNel]]
   */
  def validateString(name: String, value: Option[String]): ValidationNel[Throwable, String] =
    value.map {
      _.successNel
    }.getOrElse(ValidationException(Some(s"$name is missing")).failureNel)

  /**
   * 入力された文字列を必須でない値として検証する。
   *
   * @param name パラメータ名
   * @param value 入力値
   * @return [[scalaz.ValidationNel]]
   */
  def validateStringAsOption(name: String, value: Option[String]): ValidationNel[Throwable, Option[String]] =
    value.map {
      Some(_).successNel
    }.getOrElse(None.successNel)

  /**
   * 入力された文字列を必須の整数値として検証する。
   *
   * @param name パラメータ名
   * @param value 入力値
   * @return [[scalaz.ValidationNel]]
   */
  def validateInt(name: String, value: Option[String]): ValidationNel[Throwable, Int] =
    value.map {
      strValue =>
        Try(strValue.toInt).map {
          _.successNel
        }.recover {
          case ex =>
            ValidationException(Some(ex.getMessage), Some(ex.getCause)).failureNel
        }.get
    }.getOrElse(new ValidationException(Some(s"$name is missing")).failureNel)

  /**
   * 入力された文字列を必須でない整数値として検証する。
   *
   * @param name パラメータ名
   * @param value 入力値
   * @return [[scalaz.ValidationNel]]
   */
  def validateIntAsOption(name: String, value: Option[String]): ValidationNel[Throwable, Option[Int]] =
    value.map {
      strValue =>
        Try(strValue.toInt).map {
          Some(_).successNel
        }.recover {
          case ex =>
            ValidationException(Some(ex.getMessage), Some(ex.getCause)).failureNel
        }.get
    }.getOrElse(None.successNel)

  /**
   * 入力された文字列を必須のURLとして検証する。
   *
   * @param name パラメータ名
   * @param value 入力値
   * @return [[scalaz.ValidationNel]]
   */
  def validateURL(name: String, value: Option[String]): ValidationNel[Throwable, URL] =
    value.map {
      strValue =>
        Try(new URL(strValue)).map {
          validValue => validValue.successNel
        }.recover {
          case ex =>
            ValidationException(Some(ex.getMessage), Some(ex.getCause)).failureNel
        }.get
    }.getOrElse(new ValidationException(Some(s"$name is missing")).failureNel)

  /**
   * 入力された文字列を必須でないURLとして検証する。
   *
   * @param name パラメータ名
   * @param value 入力値
   * @return [[scalaz.ValidationNel]]
   */
  def validateURLAsOption(name: String, value: Option[String]): ValidationNel[Throwable, Option[URL]] =
    value.map {
      strValue =>
        Try(new URL(strValue)).map {
          Some(_).successNel
        }.recover {
          case ex =>
            ValidationException(Some(ex.getMessage), Some(ex.getCause)).failureNel
        }.get
    }.getOrElse(None.successNel)

  /**
   * 入力された文字列を必須の列挙値として検証する。
   *
   * @param name パラメータ名
   * @param enum 検証対象の[[scala.Enumeration]]
   * @param value 入力値
   * @return [[scalaz.ValidationNel]]
   */
  def validateEnumeration(name: String, enum: Enumeration, value: Option[String]): ValidationNel[Throwable, enum.Value] =
    value.map {
      strValue =>
        Try(enum.withName(strValue)).map {
          _.successNel
        }.recover {
          case ex =>
            ValidationException(Some(ex.getMessage), Some(ex.getCause)).failureNel
        }.get
    }.getOrElse(new ValidationException(Some(s"$name is missing")).failureNel)

  /**
   * 入力された文字列を必須でない列挙値として検証する。
   *
   * @param name パラメータ名
   * @param enum 検証対象の[[scala.Enumeration]]
   * @param value 入力値
   * @return [[scalaz.ValidationNel]]
   */
  def validateEnumerationAsOption(name: String, enum: Enumeration, value: Option[String]): ValidationNel[Throwable, Option[enum.Value]] =
    value.map {
      strValue =>
        Try(enum.withName(strValue)).map {
          Some(_).successNel
        }.recover {
          case ex =>
            ValidationException(Some(ex.getMessage), Some(ex.getCause)).failureNel
        }.get
    }.getOrElse(None.successNel)


  /**
   * 入力された文字列を必須の Identity[UUID] として検証する。
   *
   * @param name パラメータ名
   * @param value 入力値
   * @return [[scalaz.ValidationNel]]
   */
  def validateIdentifier[A <: Identifier[UUID]](name: String, value: Option[String])
                                               (apply: UUID => A = Identifier.apply): ValidationNel[Throwable, A] =
    value.map {
      strValue =>
        Try(apply(new UUID(strValue))).map {
          _.successNel
        }.recover {
          case ex =>
            ValidationException(Some(ex.getMessage), Some(ex.getCause)).failureNel
        }.get
    }.getOrElse(new ValidationException(Some(s"$name is missing")).failureNel)

  /**
   * 入力された文字列を必須でない Identity[UUID] として検証する。
   *
   * @param name パラメータ名
   * @param value 入力値
   * @return [[scalaz.ValidationNel]]
   */
  def validateIdentifierAsOption[A <: Identifier[UUID]](name: String, value: Option[String])
                                                       (apply: UUID => A = Identifier.apply): ValidationNel[Throwable, Option[A]] =
    value.map {
      strValue =>
        Try(apply(new UUID(strValue))).map {
          Some(_).successNel
        }.recover {
          case ex =>
            ValidationException(Some(ex.getMessage), Some(ex.getCause)).failureNel
        }.get
    }.getOrElse(None.successNel)

}
