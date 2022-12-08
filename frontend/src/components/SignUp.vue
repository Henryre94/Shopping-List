<template>
    <v-container>
        <h1>Sign Up</h1>
        <div class="register">
            <input type="text" v-model="name" placeholder="Enter Name" />
            <input type="text" v-model="email" placeholder="Enter Email" />
            <input type="password" v-model="password" placeholder="Enter Password" />
            <button @click="signUp"> Sign Up</button>
        </div>
    </v-container>
    
</template>

<script>
export default {
    name: 'SingUp',
    data() {
        return {
            name: '',
            email:'',
            password:''
        }
    },
    methods: {
        signUp() {
            console.warn("signup", this.name, this.email, this.password);
        }
    }
}
</script>

<style>
.register input{
    width: 300px;
    height: 400px;
    padding-left: 20px;
    display: block;
    margin-right: auto;
    margin-left: auto;
    margin-bottom: 30px;
    border: 1px solid black;
}
</style>
