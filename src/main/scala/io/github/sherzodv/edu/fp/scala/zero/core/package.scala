package io.github.sherzodv.edu.fp.scala.zero

package object core {

  def combine[A](x: A, y: A)(implicit semigroup: Semigroup[A]): A = {
    semigroup.combine(x, y)
  }

  def mempty[A](implicit monoid: Monoid[A]): A = {
    monoid.empty
  }

  def pure[F[_], A](a: A)(implicit applicative: Applicative[F]): F[A] = {
    applicative.pure(a)
  }

  /**
   * The better mathematic definition of map would be:
   * {{{def map[A, B, F[_]](f: A => B)(fa: F[A]): F[B]`}}} i.e. map transforms
   * {{{A => B}}} to {{{F[A] => F[B]}}}. But in scala it would be
   * inconvenient, due to {{{map(_ + 2)(List(1, 2, 3))}}} will not compile,
   * as compiler can't infer type for lambda's argument. So we need to use:
   * {{{def map[A, B, F[_]](fa: F[A])(f: A => B): F[B]}}}
  */
  def fmap[A, B, F[_]](fa: F[A])(f: A => B)(implicit functor: Functor[F]): F[B] = {
    functor.map(fa)(f)
  }

  def fold[F[_], A](fa: F[A])(implicit monoid: Monoid[A], foldable: Foldable[F]): A = {
    foldable.fold(fa)
  }

  def bind[F[_], A, B](fa: F[A])(f: A => F[B])(implicit monad: Monad[F]): F[B] = {
    monad.flatMap(fa)(f)
  }

  def join[F[_], A](ffa: F[F[A]])(implicit monad: Monad[F]): F[A] = {
    monad.flatten(ffa)
  }

  def fish[F[_], A, B, C](g: B => F[C])(f: A => F[B])(a: A)(implicit monad: Monad[F]) = {
    bind(f(a))(g)
  }

}
