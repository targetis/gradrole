<template>
  <div class="home row">
    <div class="col-md-3">
      <span class="hipster img-fluid rounded"></span>
    </div>
    <div class="col-md-9">
      <h1>Welcome to GradRole!</h1>
      <blockquote class="blockquote text-center">
        <p class="mb-0" v-text="$t('home.subtitle')">This is your homepage</p>
      </blockquote>

      <div>
        <div class="alert alert-success" v-if="authenticated">
          <span v-if="username" v-text="$t('home.logged.message', { username: username })">You are logged in as user "{{ username }}"</span>
        </div>

        <div v-if="!authenticated">
          <button class="btn btn-success" v-on:click="openLogin()" v-text="$t('global.messages.info.authenticated.link')">Sign in</button>
        </div>
        <div class="alert alert-warning" v-if="!authenticated">
          - Administrator (login="admin" and password="admin") <br />- User (login="user" and password="user").
        </div>
        <div class="alert alert-warning" v-if="!authenticated">
          <span v-text="$t('global.messages.info.register.noaccount')">You don't have an account yet?</span>&nbsp;
          <router-link class="alert-link" to="/register" v-text="$t('global.messages.info.register.link')"
            >Register a new account</router-link
          >
        </div>
      </div>

      <p>
        <span v-text="$t('home.like')">If you like JHipster, don't forget to give us a star on</span>
        <a href="https://github.com/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer" v-text="$t('home.github')"
          >GitHub</a
        >!
      </p>
    </div>
  </div>
</template>

<script lang="ts" src="./home.component.ts"></script>
